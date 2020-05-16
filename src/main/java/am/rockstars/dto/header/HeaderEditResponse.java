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
public class HeaderEditResponse extends AbstractHeaderEditResponse {

    private Boolean mega;

    private HeaderType type;

    private List<HeaderChildEditResponse> children;

    @Getter
    @Setter
    @ToString
    public static class HeaderChildEditResponse extends AbstractHeaderEditResponse {

        private HeaderChildType type;

        private List<HeaderChildElementEditResponse> children;

        @Getter
        @Setter
        @ToString
        public static class HeaderChildElementEditResponse extends AbstractHeaderEditResponse {

            private String queryState;

            private HeaderChildElementType type;
        }
    }
}

