package am.rockstars.exception;

public class WrongResetKeyException extends RuntimeException {

    public WrongResetKeyException(final String key) {
        super(String.format("No user was found for this Reset key '%s'", key));
    }
}
