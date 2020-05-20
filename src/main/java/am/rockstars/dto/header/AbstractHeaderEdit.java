package am.rockstars.dto.header;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
abstract class AbstractHeaderEdit extends AbstractHeader {
    private Long id;
}
