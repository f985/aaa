package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A File.
 */
@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact extends BaseEntity {

    @Column(name = "address", nullable = false, columnDefinition = "text")
    private String address;

    @Column(name = "call", nullable = false, columnDefinition = "text")
    private String call;

    @Column(name = "info", nullable = false, columnDefinition = "text")
    private String info;

    @Column(name = "mail", nullable = false)
    private String mail;
}
