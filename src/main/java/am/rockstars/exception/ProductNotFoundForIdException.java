package am.rockstars.exception;

import lombok.Getter;

import javax.persistence.EntityNotFoundException;

@Getter
public class ProductNotFoundForIdException extends EntityNotFoundException {

    private final Long id;

    public ProductNotFoundForIdException(final Long id) {
        super(String.format("Not found product by id %d", id));
        this.id = id;
    }
}
