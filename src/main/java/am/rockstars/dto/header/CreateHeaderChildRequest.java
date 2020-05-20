package am.rockstars.dto.header;

import am.rockstars.enums.HeaderChildType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHeaderChildRequest extends AbstractHeaderEdit {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private HeaderChildType type;

}
