package am.rockstars.dto;

import am.rockstars.enums.ProductCategory;
import am.rockstars.enums.ProductStatus;
import am.rockstars.enums.ProductType;
import am.rockstars.validator.UniqueProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@UniqueProduct
public class ProductPayload {

    @NotBlank(message = "Product name should not be empty")
    private String name;

    @NotBlank(message = "Product code should not be empty")
    private String productCode;

    @NotBlank(message = "Product brand should not be empty")
    private String brand;

    @NotBlank(message = "Product color should not be empty")
    private String color;

    @NotBlank(message = "Product category should not be empty")
    private String category;

    @NotNull(message = "Product category type should not be null")
    private ProductCategory categoryType;

    @NotNull(message = "Product status should not be null")
    private ProductStatus status;

    @Positive(message = "Product quantity should be positive value")
    private Integer quantity;

    @Positive(message = "Product rating should be positive value")
    private Integer rating;

    @NotNull(message = "popular should not be null")
    private Boolean popular;

    @NotNull(message = "Product type should not be null")
    private ProductType type;

    @NotNull(message = "Product availability should not be null")
    private Boolean availability;

    private String description;

    @Positive(message = "Product price should be positive value")
    private BigDecimal price;

    private List<FeatureInfo> features;

    private List<TagInfo> tags;

}
