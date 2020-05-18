package am.rockstars.controller;

import am.rockstars.entity.Product;
import am.rockstars.entity.User;
import am.rockstars.enums.ProductType;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProductControllerTest extends AbstractControllerTest  {

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should create product for provided json payload")
    @Test
    @WithMockUser(authorities = "ADMIN")
    @Sql("/test_data.sql")
    void createProduct() throws Exception {
        //Test data
        final Product product = new Product();
        product.setAvailableQuantity(10L);
        product.setDescription("Test product");
        product.setName("White Wine");
        product.setType(ProductType.WINE);
        product.setPrice(BigDecimal.TEN);
        product.setId(1L);
        final User user = new User();
        user.setId(10L);
        product.setCreatedBy(user);
        product.setCreatedAt(LocalDateTime.now());
        //API call
        mockMvc.perform(get("/api/products/{productId}", 1L))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.type").value(ProductType.WINE.name()))
               .andExpect(jsonPath("$.name").value("Kataro"))
               .andExpect(jsonPath("$.price").value(10.0));
    }
}
