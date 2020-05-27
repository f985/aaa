package am.rockstars.dto.header.edit;

import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreateHeaderRequest extends BaseHeaderEdit {

    @NotNull
    private Boolean mega;

    @NotNull
    private HeaderType type;
}
