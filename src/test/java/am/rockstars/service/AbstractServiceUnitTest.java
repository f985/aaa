package am.rockstars.service;

import am.rockstars.entity.User;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractServiceUnitTest {

    User createTestUser(final String username) {
        final User user = new User();
        user.setEmail(username);
        user.setLastName("Test user");
        return user;
    }
}
