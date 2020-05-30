package am.rockstars.dto.header.edit;

import am.rockstars.enums.HeaderChildElementType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HeaderChildElementEdit extends BaseHeaderEdit {

    private String queryState;

    private HeaderChildElementType type;
}