package am.rockstars.controller;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSqlContainer extends PostgreSQLContainer<PostgreSqlContainer> {

    private static final String IMAGE_VERSION = "postgres:11.1";

    private static final PostgreSqlContainer container;

    static {
        container = new PostgreSqlContainer().withUsername("root");
        container.start();
    }

    private PostgreSqlContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgreSqlContainer getInstance() {
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }
}
