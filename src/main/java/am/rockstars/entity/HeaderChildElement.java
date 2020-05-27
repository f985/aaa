package am.rockstars.entity;

import am.rockstars.entity.base.BaseHeaderEntity;
import am.rockstars.enums.HeaderChildElementType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "headerchildelement")
public class HeaderChildElement extends BaseHeaderEntity {

    @ManyToOne
    private HeaderChild child;

    private String queryState;

    @Enumerated(EnumType.STRING)
    private HeaderChildElementType type;
}
