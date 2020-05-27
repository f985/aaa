package am.rockstars.dto.header;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public abstract class BaseHeader {

    @NotBlank
    private String state;

    @NotBlank
    private String name;

    @NotBlank
    private String icon;
}
