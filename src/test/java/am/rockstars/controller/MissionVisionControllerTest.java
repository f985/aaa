package am.rockstars.controller;

import am.rockstars.dto.missionvision.MissionVisionRequest;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MissionVisionControllerTest extends AbstractControllerTest {

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should edit mission vision")
    @Test
    void canEditMissionVision() throws Exception {
        final MissionVisionRequest request = randomObject.nextObject(MissionVisionRequest.class);

        mockMvc.perform(put("/api/admin/mission-vision/1")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/mission-visions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("mission_vision.[1].content").value(request.getContent()),
                        jsonPath("mission_vision.[1].id").value(1L),
                        jsonPath("mission_vision.[1].heading").value(request.getHeading()),
                        jsonPath("mission_vision.[1].image").value(request.getImage()),
                        jsonPath("mission_vision.[1].sub_heading").value(request.getSubHeading())
                ));
    }

    @DisplayName("Should delete mission vision")
    @Test
    void canDeleteMissionVision() throws Exception {

        mockMvc.perform(delete("/api/admin/mission-vision/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/mission-visions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("mission_vision.[0].id").value(2L)));
    }

    @DisplayName("Should create mission vision")
    @Test
    void canCreateMissionVision() throws Exception {
        final MissionVisionRequest request = randomObject.nextObject(MissionVisionRequest.class);

        mockMvc.perform(post("/api/admin/mission-vision")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/mission-visions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("mission_vision.[2].content").value(request.getContent()),
                        jsonPath("mission_vision.[2].heading").value(request.getHeading()),
                        jsonPath("mission_vision.[2].image").value(request.getImage()),
                        jsonPath("mission_vision.[2].sub_heading").value(request.getSubHeading())
                ));
    }
}
