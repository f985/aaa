package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import am.rockstars.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}