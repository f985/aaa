package am.rockstars.dto.header.edit;

import am.rockstars.enums.HeaderChildElementType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreateHeaderChildElementRequest extends BaseHeaderEdit {

    @NotBlank
    private String queryState;

    @NotNull
    private HeaderChildElementType type;

}
