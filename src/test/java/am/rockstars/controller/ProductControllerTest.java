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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends AbstractControllerTest {

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
        mockMvc.perform(post("/api/products/")
                .content(mapper.writeValueAsString(productPayload))
                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isCreated());
        mockMvc.perform(get("/api/products/{productId}", 2L))
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
        mockMvc.perform(get("/api/products").queryParam("name", "Kataro"))
               .andDo(print())
               .andExpect((matchAll(
                       status().isOk(),
                       jsonPath("$.content[0].name").value("Kataro"),
                       jsonPath("$.content[0].id").value(1),
                       jsonPath("$.content[0].type").value(ProductType.WINE.name()),
                       jsonPath("$.content[0].price").isNumber())));
    }
}
