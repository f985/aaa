package am.rockstars.dto;

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

@Getter
@Setter
@ToString
@Builder
@UniqueProduct
public class ProductPayload {

    @NotBlank(message = "Product name should not be empty")
    private String name;

    @NotNull(message = "Product type should not be null")
    private ProductType type;

    @Positive(message = "Product available quantity should be positive value")
    private Long availableQuantity;

    private String description;

    @Positive(message = "Product price should be positive value")
    private BigDecimal price;

}
