package am.rockstars.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {UniqueProductValidator.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueProduct {
    String message() default "Not a valid field value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
