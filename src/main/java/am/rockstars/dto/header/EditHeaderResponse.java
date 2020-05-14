package am.rockstars.dto.header;

import am.rockstars.enums.HeaderChildType;
import am.rockstars.enums.HeaderType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EditHeaderResponse extends AbstractHeader {

    private Boolean mega;

    private HeaderType type;

    private List<HeaderChildResponse> children;

    @Getter
    @Setter
    @ToString
    public static class HeaderChildResponse extends AbstractHeader {

        private HeaderChildType type;

        private List<HeaderChildNodeResponse> children;

        @Getter
        @Setter
        @ToString
        public static class HeaderChildNodeResponse extends AbstractHeader {

            private HeaderChildType type;
        }
    }
}

