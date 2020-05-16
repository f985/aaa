package am.rockstars.dto.header;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
abstract class AbstractHeaderEditResponse extends AbstractHeaderResponse {
    private Long id;
}
