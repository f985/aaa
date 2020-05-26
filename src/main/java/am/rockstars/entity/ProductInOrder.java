package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * A ProductInOrder.
 */
@Getter
@Setter
@Entity
@Table(name = "product_in_order")
public class ProductInOrder extends AbstractEntity {

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "total_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(unique = true)
    private File personalization;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;
}
