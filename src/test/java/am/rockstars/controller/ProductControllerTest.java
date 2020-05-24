package am.rockstars.controller;

import am.rockstars.dto.ProductPayload;
import am.rockstars.enums.ProductType;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends AbstractControllerTest {

    private static final String BASE_PATH = "/api/products/";

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should retrieve product by id")
    @Test
    void findProductById() throws Exception {
        //Test data
        final ProductPayload productPayload = ProductPayload.builder()
                                                            .name("Vanardi")
                                                            .description("Test product")
                                                            .availableQuantity(10L)
                                                            .price(BigDecimal.TEN)
                                                            .type(ProductType.WINE)
                                                            .build();
        //API calls
        mockMvc.perform(post(BASE_PATH)
                .content(mapper.writeValueAsString(productPayload))
                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isCreated());
        mockMvc.perform(get(BASE_PATH + "{productId}", 2L))
               .andDo(print())
               .andExpect(matchAll(
                       jsonPath("$.id").value(2),
                       jsonPath("$.type").value(productPayload.getType().name()),
                       jsonPath("$.name").value(productPayload.getName()),
                       jsonPath("$.price").isNumber()));
    }

    @DisplayName("Should retrieve product with provided name")
    @Test
    void findProduct() throws Exception {
        //API call
        mockMvc.perform(get(BASE_PATH).queryParam("name", "Kataro"))
               .andDo(print())
               .andExpect((matchAll(
                       status().isOk(),
                       jsonPath("$.content[0].name").value("Kataro"),
                       jsonPath("$.content[0].id").value(1),
                       jsonPath("$.content[0].type").value(ProductType.WINE.name()),
                       jsonPath("$.content[0].price").isNumber())));
    }

    @DisplayName("Should update product for provided payload")
    @Test
    void updateProduct() throws Exception {
        //Test data
        final ProductPayload productPayload = randomObject.nextObject(ProductPayload.class);
        productPayload.setAvailableQuantity(10L);
        productPayload.setPrice(BigDecimal.TEN);
        //API call
        mockMvc.perform(put(BASE_PATH + "{productId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(productPayload)))
               .andDo(print())
               .andExpect(status().isAccepted());
        mockMvc.perform(get(BASE_PATH + "{productId}", 1L))
               .andDo(print())
               .andExpect(matchAll(
                       status().isOk(),
                       jsonPath("$.name").value(productPayload.getName()),
                       jsonPath("$.id").value(1),
                       jsonPath("$.type").value(productPayload.getType().name()),
                       jsonPath("$.price").value(productPayload.getPrice()),
                       jsonPath("$.availableQuantity").value(productPayload.getAvailableQuantity())));
    }

    @DisplayName("Should remove product for provided id")
    @Test
    void removeProduct() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "{productId}", 1L))
               .andExpect(status().isOk());
        mockMvc.perform(get(BASE_PATH + "{productId}", 1L))
               .andExpect(status().isNotFound());
    }
}
