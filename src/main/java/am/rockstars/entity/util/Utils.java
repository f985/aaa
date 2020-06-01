package am.rockstars.entity.util;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

public final class Utils {

    public static Supplier<EntityNotFoundException> illegalArg(final String message, final Object id) {
        return () -> new EntityNotFoundException(message + ": " + id);
    }
}
