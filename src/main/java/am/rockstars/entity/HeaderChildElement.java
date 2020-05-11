package am.rockstars.entity;

import am.rockstars.entity.base.AbstractHeaderEntity;
import am.rockstars.enums.HeaderChildElementType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class HeaderChildElement extends AbstractHeaderEntity {

    @Enumerated(EnumType.STRING)
    private HeaderChildElementType type;
}
