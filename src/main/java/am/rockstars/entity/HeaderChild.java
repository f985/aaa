package am.rockstars.entity;

import am.rockstars.entity.base.BaseHeaderEntity;
import am.rockstars.enums.HeaderChildType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    @OrderBy("orderNumber asc")
    private List<HeaderChildElement> children;

    public HeaderChild addElement(final HeaderChildElement element) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(element);
        element.setChild(this);
        return this;
    }
}
