package am.rockstars.dto.header;

import am.rockstars.enums.HeaderChildElementType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHeaderChildElementRequest extends AbstractHeaderEdit {

    private String queryState;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private HeaderChildElementType type;

}
