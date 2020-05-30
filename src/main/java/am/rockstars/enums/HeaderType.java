package am.rockstars.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum HeaderType {
    SUB("sub"), LINK("link");

    private final String name;

    HeaderType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
