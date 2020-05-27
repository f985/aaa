package am.rockstars.dto.header;

import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Header extends BaseHeader {

    private boolean mega;

    private HeaderType type;

    private List<HeaderChild> children;


}


