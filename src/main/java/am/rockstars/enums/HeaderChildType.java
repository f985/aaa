package am.rockstars.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum HeaderChildType {
    SUB("sub"), SUB_CHILD("subChild"), LINK("link"), QUERY_PARAMS("queryParams");

    @JsonValue
    private String name;

    HeaderChildType(String name) {
        this.name = name;
    }
}
