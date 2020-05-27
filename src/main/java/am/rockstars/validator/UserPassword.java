package am.rockstars.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = {UserPasswordValidator.class})
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserPassword {
    String message() default "Incorrect password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
