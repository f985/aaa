package am.rockstars.controller;

import am.rockstars.dto.contact.ContactRequest;
import am.rockstars.dto.header.edit.CreateHeaderChildElementRequest;
import am.rockstars.dto.header.edit.CreateHeaderChildRequest;
import am.rockstars.dto.header.edit.CreateHeaderRequest;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ContactControllerTest extends AbstractControllerTest {

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should edit contact")
    @Test
    void createContacts() throws Exception {
        final ContactRequest request = randomObject.nextObject(ContactRequest.class);

        mockMvc.perform(post("/api/admin/contact")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("$[1].address").value(request.getAddress()),
                        jsonPath("$[1].call").value(request.getCall()),
                        jsonPath("$[1].info").value(request.getInfo()),
                        jsonPath("$[1].mail").value(request.getMail())));
    }

    @DisplayName("Should edit contact")
    @Test
    void editContacts() throws Exception {
        final ContactRequest request = randomObject.nextObject(ContactRequest.class);

        mockMvc.perform(put("/api/admin/contact/edit/1")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("$[0].address").value(request.getAddress()),
                        jsonPath("$[0].call").value(request.getCall()),
                        jsonPath("$[0].info").value(request.getInfo()),
                        jsonPath("$[0].mail").value(request.getMail())));
    }

    @DisplayName("Should get edit contact")
    @Test
    void getEditContacts() throws Exception {
        mockMvc.perform(get("/api/admin/contact/edit")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("$[0].address").exists(),
                        jsonPath("$[0].call").exists(),
                        jsonPath("$[0].info").exists(),
                        jsonPath("$[0].mail").exists()));
    }

    @DisplayName("Should delete contact")
    @Test
    void deleteContacts() throws Exception {
        mockMvc.perform(delete("/api/admin/contact/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk()));

        mockMvc.perform(get("/api/contact/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isNotFound()));

    }
}
