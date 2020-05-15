package am.rockstars.validator;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserPasswordValidator implements ConstraintValidator<UserPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password.length() < 6) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Incorrect password, Password should contains more then 5 letters")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
