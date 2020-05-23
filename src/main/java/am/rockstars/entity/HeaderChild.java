package am.rockstars.entity;

import am.rockstars.entity.base.BaseHeaderEntity;
import am.rockstars.enums.HeaderChildType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "headerchild")
public class HeaderChild extends BaseHeaderEntity {

    @Enumerated(EnumType.STRING)
    private HeaderChildType type;

    @ManyToOne
    private Header header;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<HeaderChildElement> children;
}
