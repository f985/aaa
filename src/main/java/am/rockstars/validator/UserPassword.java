package am.rockstars.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = {UserPasswordValidator.class})
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserPassword {
    String message() default "Incorrect password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
