package am.rockstars.entity;

import am.rockstars.entity.base.AbstractHeaderEntity;
import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Header extends AbstractHeaderEntity {

    private Boolean mega;

    @Enumerated(EnumType.STRING)
    private HeaderType type;

    @ManyToMany
    private List<HeaderChild> children;

}
