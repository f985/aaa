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
public class HeaderResponse extends AbstractHeaderResponse {

    private Boolean mega;

    private HeaderType type;

    private List<HeaderChildResponse> children;

    @Getter
    @Setter
    @ToString
    public static class HeaderChildResponse extends AbstractHeaderResponse {

        private HeaderChildType type;

        private List<HeaderChildElementResponse> children;

        @Getter
        @Setter
        @ToString
        public static class HeaderChildElementResponse extends AbstractHeaderResponse {

            private String queryState;

            private HeaderChildElementType type;
        }
    }
}


