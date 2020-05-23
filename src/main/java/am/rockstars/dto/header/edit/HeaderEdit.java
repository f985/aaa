package am.rockstars.dto.header.edit;

import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HeaderEdit extends BaseHeaderEdit {

    private boolean mega;

    private HeaderType type;

    private List<HeaderChildEdit> children;


}

