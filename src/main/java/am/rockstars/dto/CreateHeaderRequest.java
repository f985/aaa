package am.rockstars.dto;

import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHeaderRequest {

    private String state;

    private String name;

    private String icon;

    private Boolean mega;

    private HeaderType type;
}
