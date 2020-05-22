package am.rockstars.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseHeaderEntity extends BaseEntity {

    private String state;

    private String name;

    private String icon;

}
