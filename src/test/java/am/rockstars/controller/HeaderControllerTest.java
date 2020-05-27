package am.rockstars.controller;

import am.rockstars.dto.header.edit.CreateHeaderChildElementRequest;
import am.rockstars.dto.header.edit.CreateHeaderChildRequest;
import am.rockstars.dto.header.edit.CreateHeaderRequest;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HeaderControllerTest extends AbstractControllerTest {

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should create header")
    @Test
    void EditHeaders() throws Exception {
        final CreateHeaderRequest createHeaderRequest = createHeaderRequest(1L);

        executeCreateHeaderRequest(createHeaderRequest);

        mockMvc.perform(get("/api/header")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("$[0].name").value(createHeaderRequest.getName()),
                        jsonPath("$[0].mega").value(createHeaderRequest.getMega()),
                        jsonPath("$[0].icon").value(createHeaderRequest.getIcon()),
                        jsonPath("$[0].type").value(createHeaderRequest.getType().getName()),
                        jsonPath("$[0].state").value(createHeaderRequest.getState())));
    }

    @DisplayName("Should create header child")
    @Test
    void EditHeaderChild() throws Exception {
        final Long headerId = 2L;
        executeCreateHeaderRequest(createHeaderRequest(headerId));
        final CreateHeaderChildRequest childRequest = createChildRequest(headerId);
        executeCreateChildRequest(headerId, childRequest);

        mockMvc.perform(get("/api/header")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("$[1].children[0].name").value(childRequest.getName()),
                        jsonPath("$[1].children[0].icon").value(childRequest.getIcon()),
                        jsonPath("$[1].children[0].type").value(childRequest.getType().getName()),
                        jsonPath("$[1].children[0].state").value(childRequest.getState())));
    }

    @DisplayName("Should create header child element")
    @Test
    void EditHeaderChildElement() throws Exception {
        final Long id = 3L;
        executeCreateHeaderRequest(createHeaderRequest(id));
        executeCreateChildRequest(id, createChildRequest(id));
        final CreateHeaderChildElementRequest elementRequest = randomObject.nextObject(CreateHeaderChildElementRequest.class);
        elementRequest.setId(id);
        elementRequest.setOrderNumber(id.intValue());

        mockMvc.perform(post("/api/admin/header/child/" + id + "/element")
                .content(mapper.writeValueAsString(elementRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/header")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("$[2].children[0].children[0].name").value(elementRequest.getName()),
                        jsonPath("$[2].children[0].children[0].icon").value(elementRequest.getIcon()),
                        jsonPath("$[2].children[0].children[0].type").value(elementRequest.getType().getName()),
                        jsonPath("$[2].children[0].children[0].state").value(elementRequest.getState())));
    }

    private CreateHeaderChildRequest createChildRequest(final Long childId) {
        final CreateHeaderChildRequest childRequest = randomObject.nextObject(CreateHeaderChildRequest.class);
        childRequest.setId(childId);
        childRequest.setOrderNumber(childId.intValue());
        return childRequest;
    }

    private CreateHeaderRequest createHeaderRequest(final Long headerId) {
        final CreateHeaderRequest createHeaderRequest = randomObject.nextObject(CreateHeaderRequest.class);
        createHeaderRequest.setId(headerId);
        createHeaderRequest.setOrderNumber(headerId.intValue());
        return createHeaderRequest;
    }

    private void executeCreateChildRequest(Long headerId, CreateHeaderChildRequest childRequest) throws Exception {
        mockMvc.perform(post("/api/admin/header/" + headerId + "/child")
                .content(mapper.writeValueAsString(childRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void executeCreateHeaderRequest(CreateHeaderRequest createHeaderRequest) throws Exception {
        mockMvc.perform(post("/api/admin/header")
                .content(mapper.writeValueAsString(createHeaderRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
