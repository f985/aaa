package am.rockstars.validator;

import am.rockstars.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailNotExistValidator implements ConstraintValidator<UniqueProduct, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        if (userRepository.findByEmail(email).isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("User with Email '%s' exist", email))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
