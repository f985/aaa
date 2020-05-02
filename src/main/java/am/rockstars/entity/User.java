package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column
    private String email;

    @Column
    private String token;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private long barrels;
}