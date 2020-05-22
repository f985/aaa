package am.rockstars.dto.header;

import am.rockstars.enums.HeaderChildType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HeaderChild extends BaseHeader {

    private HeaderChildType type;

    private List<HeaderChildElement> children;


}