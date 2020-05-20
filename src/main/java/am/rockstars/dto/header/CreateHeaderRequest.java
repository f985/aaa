package am.rockstars.dto.header;

import am.rockstars.enums.HeaderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHeaderRequest extends AbstractHeaderEdit {

    private Boolean mega;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private HeaderType type;
}
