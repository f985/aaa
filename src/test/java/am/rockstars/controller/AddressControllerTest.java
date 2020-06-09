package am.rockstars.controller;

import am.rockstars.dto.AddressPayload;
import am.rockstars.enums.AddressType;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddressControllerTest extends AbstractControllerTest {

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should Create address")
    @Test
    void addAddress() throws Exception {
        final AddressPayload addressRequest = getAddressPayload();
        final String id = executeCreateAddressRequest(addressRequest);
        mockMvc.perform(get("/api/addresses/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(match(addressRequest));
    }

    @DisplayName("Should Update address")
    @Test
    void updateAddress() throws Exception {
        final AddressPayload addressRequest = getAddressPayload();
        final String id = executeCreateAddressRequest(addressRequest);
        addressRequest.setStreet("2");
        addressRequest.setCountry("AM");
        executeUpdateAddressRequest(addressRequest, id);
        mockMvc.perform(get("/api/addresses/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(match(addressRequest));
    }

    @DisplayName("Should Delete address")
    @Test
    void deleteAddress() throws Exception {
        final AddressPayload addressRequest = getAddressPayload();
        final String id = executeCreateAddressRequest(addressRequest);
        executeDeleteAddressRequest(id);
        mockMvc.perform(get("/api/addresses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private AddressPayload getAddressPayload() {
        final AddressPayload addressRequest = new AddressPayload();
        addressRequest.setUserId(1L);
        addressRequest.setType(AddressType.BILLING);
        addressRequest.setZipcode("90065");
        addressRequest.setAddress("1");
        addressRequest.setCity("1");
        addressRequest.setBuilding("1");
        addressRequest.setCountry("1");
        addressRequest.setState("1");
        addressRequest.setStreet("1");
        return addressRequest;
    }


    private ResultMatcher match(final AddressPayload addressRequest) {
        return matchAll(status().isOk(),
                jsonPath("userId").value(addressRequest.getUserId()),
                jsonPath("address").value(addressRequest.getAddress()),
                jsonPath("building").value(addressRequest.getBuilding()),
                jsonPath("city").value(addressRequest.getCity()),
                jsonPath("country").value(addressRequest.getCountry()),
                jsonPath("state").value(addressRequest.getState()),
                jsonPath("street").value(addressRequest.getStreet()),
                jsonPath("type").value(addressRequest.getType().toString()),
                jsonPath("zipcode").value(addressRequest.getZipcode()));
    }


    private String executeCreateAddressRequest(AddressPayload createAddressRequest) throws Exception {
        return mockMvc.perform(post("/api/addresses")
                .content(mapper.writeValueAsString(createAddressRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
    }

    private void executeUpdateAddressRequest(AddressPayload createAddressRequest, final String id) throws Exception {
        mockMvc.perform(put("/api/addresses/" + id)
                .content(mapper.writeValueAsString(createAddressRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void executeDeleteAddressRequest(final String id) throws Exception {
        mockMvc.perform(delete("/api/addresses/" + id))
                .andExpect(status().isOk());
    }

}
