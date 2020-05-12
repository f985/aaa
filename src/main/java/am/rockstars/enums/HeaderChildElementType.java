package am.rockstars.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum HeaderChildElementType {
    LINK("link"), QUERY_PARAMS("queryParams");

    @JsonValue
    private String name;

    HeaderChildElementType(String name) {
        this.name = name;
    }
}
