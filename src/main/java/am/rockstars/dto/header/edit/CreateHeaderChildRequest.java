package am.rockstars.dto.header.edit;

import am.rockstars.enums.HeaderChildType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreateHeaderChildRequest extends BaseHeaderEdit {

    @NotNull
    private HeaderChildType type;

}
