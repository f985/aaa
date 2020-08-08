package am.rockstars.controller;

import am.rockstars.dto.FeatureInfo;
import am.rockstars.dto.ProductPayload;
import am.rockstars.dto.TagInfo;
import am.rockstars.enums.ProductCategory;
import am.rockstars.enums.ProductStatus;
import am.rockstars.enums.ProductType;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        final List<FeatureInfo> features = new ArrayList<>();
        features.add(new FeatureInfo("feature1"));
        features.add(new FeatureInfo("feature2"));

        final List<TagInfo> tags = new ArrayList<>();
        tags.add(new TagInfo("tag1"));
        tags.add(new TagInfo("tag2"));

        final ProductPayload productPayload = ProductPayload.builder()
                .name("Vanardi")
                .description("Test product")
                .availability(true)
                .popular(false)
                .productCode("0001")
                .color("green")
                .brand("LA")
                .category("LA")
                .categoryType(ProductCategory.MEN)
                .status(ProductStatus.NEW)
                .quantity(1)
                .rating(1)
                .price(BigDecimal.TEN)
                .type(ProductType.WINE)
                .features(features)
                .tags(tags)
                .build();
        //API calls
        mockMvc.perform(post(BASE_PATH)
                .accept(MediaType.APPLICATION_JSON)
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
                        jsonPath("$.brand").value(productPayload.getBrand()),
                        jsonPath("$.category").value(productPayload.getCategory()),
                        jsonPath("$.description").value(productPayload.getDescription()),
                        jsonPath("$.productCode").value(productPayload.getProductCode()),
                        jsonPath("$.color").value(productPayload.getColor()),
                        jsonPath("$.availability").value(productPayload.getAvailability()),
                        jsonPath("$.popular").value(productPayload.getPopular()),
                        jsonPath("$.quantity").value(productPayload.getQuantity()),
                        jsonPath("$.rating").value(productPayload.getRating()),
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
        productPayload.setProductCode("0001");
        productPayload.setBrand("LA");
        productPayload.setCategory("MEN");
        productPayload.setColor("GREEN");
        productPayload.setQuantity(1);
        productPayload.setRating(1);
        productPayload.setCategoryType(ProductCategory.MEN);
        productPayload.setStatus(ProductStatus.NEW);
        productPayload.setAvailability(true);
        productPayload.setPopular(false);
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
                        jsonPath("$.id").value(1),
                        jsonPath("$.type").value(productPayload.getType().name()),
                        jsonPath("$.name").value(productPayload.getName()),
                        jsonPath("$.brand").value(productPayload.getBrand()),
                        jsonPath("$.category").value(productPayload.getCategory()),
                        jsonPath("$.description").value(productPayload.getDescription()),
                        jsonPath("$.productCode").value(productPayload.getProductCode()),
                        jsonPath("$.color").value(productPayload.getColor()),
                        jsonPath("$.availability").value(productPayload.getAvailability()),
                        jsonPath("$.popular").value(productPayload.getPopular()),
                        jsonPath("$.quantity").value(productPayload.getQuantity()),
                        jsonPath("$.rating").value(productPayload.getRating()),
                        jsonPath("$.price").isNumber()));
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
