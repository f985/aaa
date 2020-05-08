package am.rockstars.service;

import am.rockstars.entity.User;

public abstract class AbstractServiceUnitTest {

    User createTestUser(final String username) {
        final User user = new User();
        user.setEmail(username);
        user.setName("Test user");
        return user;
    }
}
