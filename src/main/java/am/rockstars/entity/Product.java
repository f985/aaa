package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import am.rockstars.enums.ProductCategory;
import am.rockstars.enums.ProductStatus;
import am.rockstars.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "category", nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", nullable = false)
    private ProductCategory categoryType;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "availability", nullable = false)
    private boolean availability;

    @Column(name = "popular", nullable = false)
    private boolean popular;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProductType type;

    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private List<Feature> features;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby", updatable = false)
    private User createdBy;
}
