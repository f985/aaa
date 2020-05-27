package am.rockstars.dto.header;

import am.rockstars.enums.HeaderChildElementType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HeaderChildElement extends BaseHeader {

    private String queryState;

    private HeaderChildElementType type;
}