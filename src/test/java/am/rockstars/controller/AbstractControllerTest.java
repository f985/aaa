package am.rockstars.controller;

import am.rockstars.enums.UserRole;
import am.rockstars.security.domain.UserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "artur.vaganyan96@gmail.com", authorities = "ADMIN")
@Transactional
@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    EasyRandom randomObject = new EasyRandom();

    UserDetails manager() {
        UserPrincipal userDetails = new UserPrincipal();
        userDetails.setRole(UserRole.MANAGER);
        userDetails.setEmail("artur.vaganyan96@gmail.com");
        userDetails.setId(1L);
        return userDetails;
    }
}
