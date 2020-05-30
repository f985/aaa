package am.rockstars.entity.util;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

public final class Utils {

    public static Supplier<EntityNotFoundException> assertEntityNotFound(final String message, final Object id) {
        return () -> new EntityNotFoundException(message + ": " + id);
    }
}
