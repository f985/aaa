package am.rockstars.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {UniqueProductValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EmailNotExist {
    String message() default "User with email already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
