package am.rockstars.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class AbstractHeaderEntity extends AbstractEntity implements Serializable {

    private String state;

    private String name;

    private String icon;

}
