package am.rockstars.controller;

import am.rockstars.dto.AddressPayload;
import am.rockstars.dto.header.edit.CreateHeaderChildElementRequest;
import am.rockstars.dto.header.edit.CreateHeaderChildRequest;
import am.rockstars.enums.AddressType;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddressControllerTest extends AbstractControllerTest {

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should create header")
    @Test
    void EditHeaders() throws Exception {
        final AddressPayload addressRequest = randomObject.nextObject(AddressPayload.class);
        addressRequest.setUserId(1L);
        addressRequest.setType(AddressType.BILLING);
        addressRequest.setZipcode("90065");
        addressRequest.setAddress("1");
        addressRequest.setCity("1");
        addressRequest.setBuilding("1");
        addressRequest.setCountry("1");
        addressRequest.setState("1");
        addressRequest.setStreet("1");
        executeCreateAddressRequest(addressRequest);

        mockMvc.perform(get("/api/addresses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("userId").value(addressRequest.getUserId()),
                        jsonPath("address").value(addressRequest.getAddress()),
                        jsonPath("building").value(addressRequest.getBuilding()),
                        jsonPath("city").value(addressRequest.getCity()),
                        jsonPath("country").value(addressRequest.getCountry()),
                        jsonPath("state").value(addressRequest.getState()),
                        jsonPath("street").value(addressRequest.getStreet()),
                        jsonPath("type").value(addressRequest.getType().toString()),
                        jsonPath("zipcode").value(addressRequest.getZipcode())));
    }

    @DisplayName("Should create header child")
    @Test
    @WithUserDetails("admin")
    void EditHeaderChild() throws Exception {
        final Long headerId = 2L;
        executeCreateAddressRequest(createAddressPayload(headerId));
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
        executeCreateAddressRequest(createAddressPayload(id));
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

    private AddressPayload createAddressPayload(final Long headerId) {
        final AddressPayload createHeaderRequest = randomObject.nextObject(AddressPayload.class);
        return createHeaderRequest;
    }

    private void executeCreateChildRequest(Long headerId, CreateHeaderChildRequest childRequest) throws Exception {
        mockMvc.perform(post("/api/admin/header/" + headerId + "/child")
                .content(mapper.writeValueAsString(childRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void executeCreateAddressRequest(AddressPayload createAddressRequest) throws Exception {
        mockMvc.perform(post("/api/addresses")
                .content(mapper.writeValueAsString(createAddressRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
