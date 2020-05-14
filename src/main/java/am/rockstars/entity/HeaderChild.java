package am.rockstars.entity;

import am.rockstars.entity.base.AbstractHeaderEntity;
import am.rockstars.enums.HeaderChildType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "headerChild")
public class HeaderChild extends AbstractHeaderEntity {

    @Enumerated(EnumType.STRING)
    private HeaderChildType type;

    @ManyToOne
    private Header header;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<HeaderChildElement> children;
}
