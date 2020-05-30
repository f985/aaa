package am.rockstars.dto.header.edit;

import am.rockstars.enums.HeaderChildType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HeaderChildEdit extends BaseHeaderEdit {

    private HeaderChildType type;

    private List<HeaderChildElementEdit> children;

}