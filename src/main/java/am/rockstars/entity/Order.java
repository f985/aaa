package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import am.rockstars.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Order.
 */
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private Set<ProductInOrder> products = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private User user;
}
