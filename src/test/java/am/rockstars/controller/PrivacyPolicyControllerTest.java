package am.rockstars.controller;

import am.rockstars.dto.privacypolicy.PrivacyPolicyRequest;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PrivacyPolicyControllerTest extends AbstractControllerTest {

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should edit mission vision")
    @Test
    void canEditPrivacyPolicy() throws Exception {
        final PrivacyPolicyRequest request = randomObject.nextObject(PrivacyPolicyRequest.class);

        mockMvc.perform(put("/api/admin/privacy-policy/1")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/privacy-policies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("[3].content").value(request.getContent()),
                        jsonPath("[3].id").value(1L),
                        jsonPath("[3].name").value(request.getName())
                ));
    }

    @DisplayName("Should delete mission vision")
    @Test
    void canDeletePrivacyPolicy() throws Exception {

        mockMvc.perform(delete("/api/admin/privacy-policy/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/privacy-policies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("[0].id").value(2L)));
    }

    @DisplayName("Should create mission vision")
    @Test
    void canCreatePrivacyPolicy() throws Exception {
        final PrivacyPolicyRequest request = randomObject.nextObject(PrivacyPolicyRequest.class);

        mockMvc.perform(post("/api/admin/privacy-policy")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/privacy-policies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("[4].content").value(request.getContent()),
                        jsonPath("[4].name").value(request.getName())
                ));
    }
}
