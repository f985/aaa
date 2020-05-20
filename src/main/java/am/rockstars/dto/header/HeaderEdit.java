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
public class HeaderEdit extends AbstractHeaderEdit {

    private Boolean mega;

    private HeaderType type;

    private List<HeaderChildEdit> children;

    @Getter
    @Setter
    @ToString
    public static class HeaderChildEdit extends AbstractHeaderEdit {

        private HeaderChildType type;

        private List<HeaderChildElementEdit> children;

        @Getter
        @Setter
        @ToString
        public static class HeaderChildElementEdit extends AbstractHeaderEdit {

            private String queryState;

            private HeaderChildElementType type;
        }
    }
}

