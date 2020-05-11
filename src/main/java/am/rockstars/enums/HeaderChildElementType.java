package am.rockstars.enums;

public enum HeaderChildElementType {
    LINK("link"), QUERY_PARAMS("queryParams");

    private String name;

    HeaderChildElementType(String name) {
        this.name = name;
    }
}
