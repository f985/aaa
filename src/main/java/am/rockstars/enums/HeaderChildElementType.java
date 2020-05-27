package am.rockstars.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum HeaderChildElementType {
    LINK("link"), QUERY_PARAMS("queryParams");


    private final String name;

    HeaderChildElementType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
