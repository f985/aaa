package am.rockstars.entity;

import am.rockstars.entity.base.BaseHeaderEntity;
import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "header")
public class Header extends BaseHeaderEntity{

    private boolean mega;

    @Enumerated(EnumType.STRING)
    private HeaderType type;

    @OneToMany(mappedBy = "header", cascade = CascadeType.ALL)
    @OrderBy("orderNumber asc")
    private List<HeaderChild> children;

}
