package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import am.rockstars.enums.ShipmentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * A Shipment.
 */
@Getter
@Setter
@Entity
@Table(name = "shipment")
public class Shipment extends BaseEntity {

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
