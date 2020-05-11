package am.rockstars.enums;

public enum HeaderChildType {
    SUB("sub"), SUB_CHILD("subChild"), LINK("link"), QUERY_PARAMS("queryParams");

    private String name;

    HeaderChildType(String name) {
        this.name = name;
    }
}
