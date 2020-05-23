package am.rockstars.validator;

import am.rockstars.dto.ProductPayload;
import am.rockstars.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueProductValidator implements ConstraintValidator<UniqueProduct, ProductPayload> {

    private final ProductRepository productRepository;

    @Override
    public boolean isValid(final ProductPayload value, final ConstraintValidatorContext context) {
        if (productRepository.findByNameAndType(value.getName(), value.getType()).isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Product type and title should be unique").addConstraintViolation();
            return false;
        }
        return true;
    }
}
