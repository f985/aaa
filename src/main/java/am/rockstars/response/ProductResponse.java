package am.rockstars.response;

import am.rockstars.enums.ProductType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductResponse {

    private Long id;

    private ProductType type;

    private String name;

    private BigDecimal price;

    private Long availableQuantity;

    private byte[] image;
}
