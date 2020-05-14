package am.rockstars.dto.header;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
abstract class AbstractHeader {
    private Long id;

    private String state;

    private String name;

    private String icon;
}
