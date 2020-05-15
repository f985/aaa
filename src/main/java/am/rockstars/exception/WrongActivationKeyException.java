package am.rockstars.exception;

public class WrongActivationKeyException extends RuntimeException {

    public WrongActivationKeyException(final String key) {
        super(String.format("No user was found for this activation key '%s'", key));
    }
}
