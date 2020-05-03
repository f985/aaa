package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import am.rockstars.entity.enumeration.ShipmentType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Shipment.
 */
@Entity
@Table(name = "shipment")
@Getter
@Setter
public class Shipment extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ShipmentType type;

    @NotNull
    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @Column(name = "carrier")
    private String carrier;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @OneToOne
    @JoinColumn(unique = true)
    private Order order;
}
