package am.rockstars.dto;

import am.rockstars.enums.ProductType;
import am.rockstars.validator.UniqueProduct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@UniqueProduct
public class ProductPayload {

    private String name;

    private ProductType type;

    private String quantity;

    private String description;
}
