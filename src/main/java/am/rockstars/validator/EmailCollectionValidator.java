package am.rockstars.validator;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class EmailCollectionValidator implements ConstraintValidator<ValidEmailCollection, Collection<String>> {

    @Override
    public boolean isValid(final Collection<String> value, final ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        EmailValidator validator = new EmailValidator();
        for (String s : value) {
            if (!validator.isValid(s, context) || s.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
