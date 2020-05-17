package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import am.rockstars.enums.AddressType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address extends AbstractEntity {

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType type;

    @Column(name = "address")
    private String address;

    @Column(name = "building")
    private String building;

    @Column(name = "street")
    private String street;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", updatable = false)
    private User createdBy;

}
