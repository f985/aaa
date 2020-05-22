package am.rockstars.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum HeaderType {
    SUB("sub"), LINK("link");

    @JsonValue
    private final String name;

    HeaderType(String name) {
        this.name = name;
    }
}
