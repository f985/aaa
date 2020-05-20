package am.rockstars.dto.header;

import am.rockstars.enums.HeaderChildElementType;
import am.rockstars.enums.HeaderChildType;
import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Header extends AbstractHeader {

    private Boolean mega;

    private HeaderType type;

    private List<HeaderChild> children;

    @Getter
    @Setter
    @ToString
    public static class HeaderChild extends AbstractHeader {

        private HeaderChildType type;

        private List<HeaderChildElement> children;

        @Getter
        @Setter
        @ToString
        public static class HeaderChildElement extends AbstractHeader {

            private String queryState;

            private HeaderChildElementType type;
        }
    }
}


