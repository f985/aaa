package am.rockstars.controller;

import am.rockstars.dto.AboutInformationPayload;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AboutInformationControllerTest extends AbstractControllerTest {

    private static final String BASE_PATH = "/api/about/";

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should retrieve About information by id")
    @Test
    void findAboutById() throws Exception {
        //Test data
        final Long id = 1L;
        //API calls
        mockMvc.perform(get(BASE_PATH + id))
                .andDo(print())
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("$.id").value(id)));
    }

    @DisplayName("Should update About information by payload")
    @Test
    void updateAboutInformation() throws Exception {
        //Test data
        final Long id = 1L;
        final AboutInformationPayload aboutInformationPayload = createAboutInformationPayload(id);

        //API calls
        mockMvc.perform(put("/api/admin/about")
                .content(mapper.writeValueAsString(aboutInformationPayload))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get(BASE_PATH + id))
                .andDo(print())
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("$.id").value(id),
                        jsonPath("$.content").value(aboutInformationPayload.getContent()),
                        jsonPath("$.heading").value(aboutInformationPayload.getHeading()),
                        jsonPath("$.image").value(aboutInformationPayload.getImage()),
                        jsonPath("$.sub_heading").value(aboutInformationPayload.getSubHeading()
                        )));
    }

    private AboutInformationPayload createAboutInformationPayload(final Long id) {
        final AboutInformationPayload aboutInformationPayload = randomObject.nextObject(AboutInformationPayload.class);
        aboutInformationPayload.setId(id);
        return aboutInformationPayload;
    }

}
