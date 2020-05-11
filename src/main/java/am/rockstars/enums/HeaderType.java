package am.rockstars.enums;

import lombok.Getter;

@Getter
public enum HeaderType {
    SUB("sub"), LINK("link");

    private final String name;

    HeaderType(String name) {
        this.name = name;
    }
}
