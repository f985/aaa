package am.rockstars.entity;

import am.rockstars.entity.base.AbstractHeaderEntity;
import am.rockstars.enums.HeaderChildType;
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
public class HeaderChild extends AbstractHeaderEntity {

    @Enumerated(EnumType.STRING)
    private HeaderChildType type;

    @ManyToMany
    private List<HeaderChildElement> children;
}
