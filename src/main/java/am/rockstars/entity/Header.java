package am.rockstars.entity;

import am.rockstars.entity.base.BaseHeaderEntity;
import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "header")
public class Header extends BaseHeaderEntity {

    private Boolean mega;

    @Enumerated(EnumType.STRING)
    private HeaderType type;

    @OneToMany(mappedBy = "header", cascade = CascadeType.ALL)
    private List<HeaderChild> children;

}
