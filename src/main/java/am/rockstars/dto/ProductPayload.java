package am.rockstars.dto;

import am.rockstars.enums.ProductType;
import am.rockstars.validator.UniqueProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@UniqueProduct
public class ProductPayload {

    private String name;

    private ProductType type;

    private String quantity;

    private String description;

    @Positive(message = "Product price should be positive value")
    private BigDecimal price;
}
