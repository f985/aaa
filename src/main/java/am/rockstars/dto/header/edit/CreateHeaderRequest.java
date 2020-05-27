package am.rockstars.dto.header.edit;

import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHeaderRequest extends BaseHeaderEdit {

    private Boolean mega;

    private HeaderType type;
}
