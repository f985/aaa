package am.rockstars.controller;

import am.rockstars.NverApp;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTestg
@AutoConfigureMockMvc
@WithMockUser(username = "artur.vaganyan96@gmail.com", authorities = "ADMIN")
@Transactional
@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    EasyRandom randomObject = new EasyRandom();
}
