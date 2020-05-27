package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import am.rockstars.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * A Product.
 */
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProductType type;

    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "available_quantity", nullable = false)
    private Long availableQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby", updatable = false)
    private User createdBy;

}
