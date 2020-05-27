package am.rockstars.dto.header.edit;

import am.rockstars.enums.HeaderChildType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHeaderChildRequest extends BaseHeaderEdit {

    private HeaderChildType type;

}
