package am.rockstars.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundForIdException extends RuntimeException {

    private final Long id;

    public ProductNotFoundForIdException(final Long id) {
        super(String.format("Not found product by id %d", id));
        this.id = id;
    }
}
