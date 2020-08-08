package am.rockstars.dto;

import am.rockstars.enums.ProductCategory;
import am.rockstars.enums.ProductStatus;
import am.rockstars.enums.ProductType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class GetProductDetailedInfoResponse {

    private Long id;

    private String name;

    private String brand;

    private String category;

    private ProductCategory categoryType;

    private String color;

    private String productCode;

    private boolean availability;

    private boolean popular;

    private ProductType type;

    private BigDecimal price;

    private Integer rating;

    private Integer quantity;

    private ProductStatus status;

    private String description;

    private List<FeatureInfo> features;

    private List<TagInfo> tags;

    private Long createdById;
}
