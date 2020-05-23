package am.rockstars.service;

import am.rockstars.entity.User;

public abstract class AbstractServiceUnitTest {

    User createTestUser() {
        return createTestUser("test@user.em");
    }

    User createTestUser(final String username) {
        final User user = new User();
        user.setEmail(username);
        user.setLastName("Test user");
        return user;
    }
}
