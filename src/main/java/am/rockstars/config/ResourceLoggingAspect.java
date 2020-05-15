package am.rockstars.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Aspect
@Component
public class ResourceLoggingAspect {

    private final HttpServletRequest request;

    public ResourceLoggingAspect(final HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)|| @annotation(org.springframework.web.bind.annotation.PatchMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void request() {
    }

    @Before("request()")
    public void beforeRequest(final JoinPoint joinPoint) {
        final Logger logger = getLogger(joinPoint);
        if (logger.isDebugEnabled()) {
            logger.debug(("Called {}:{}{}"), request.getMethod(), request.getRequestURI(), getBody(joinPoint));
        }
    }

    private String getBody(final JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Annotation[][] annotationMatrix = methodSignature.getMethod().getParameterAnnotations();
        final OptionalInt index = IntStream.range(0, annotationMatrix.length)
                .filter(i -> Stream.of(annotationMatrix[i])
                        .anyMatch(an -> an instanceof RequestBody))
                .findFirst();
        return index.isPresent() ? String.format(" bean: %s", joinPoint.getArgs()[index.getAsInt()]) : "";
    }

    @AfterReturning(value = "request()", returning = "response")
    public void afterReturning(final JoinPoint joinPoint, final Object response) {
        getLogger(joinPoint).debug(("Successfully finished {}:{} Response: {}"), request.getMethod(),
                request.getRequestURI(), response);
    }

    @AfterThrowing(value = "request()", throwing = "ex")
    public void logExceptions(final JoinPoint joinPoint, final Throwable ex) {
        getLogger(joinPoint).error("Error in  {}:{}", request.getMethod(), request.getRequestURI(), ex);
    }

    public Logger getLogger(final JoinPoint joinPoint) {
        return LoggerFactory.getLogger(ClassUtils.getUserClass(joinPoint.getThis().getClass()));
    }
}
