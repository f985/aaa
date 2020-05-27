package am.rockstars.entity.util;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public final class Utils {

    public static void assertEntityNotPresent(final Optional object, final String message, final Object id) {
        if (object.isEmpty()) {
            throw new EntityNotFoundException(message + ": " + id);
        }
    }
}
