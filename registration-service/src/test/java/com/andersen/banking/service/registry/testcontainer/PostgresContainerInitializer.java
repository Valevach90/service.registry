package com.andersen.banking.service.registry.testcontainer;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

    private static final String IMAGE_VERSION = "postgres:13.2";

    private static PostgreSQLContainer<?> container;

    @Override
    public void initialize(GenericApplicationContext genericApplicationContext) {
        instanceContainer();

        String jdbcUrl =
                String.format("jdbc:postgresql://%s:%d/postgres?currentSchema=public",
                        container.getContainerIpAddress(),
                        container.getFirstMappedPort()
                );

        TestPropertyValues.of(
                "spring.datasource.url=" + jdbcUrl,
                "spring.datasource.username=" + container.getUsername(),
                "spring.datasource.password=" + container.getPassword(),
                "spring.flyway.enabled=true"
        ).applyTo(genericApplicationContext.getEnvironment());
    }

    private void instanceContainer() {
        if (container == null) {
            container = new PostgreSQLContainer<>(IMAGE_VERSION);
            container
                    .withExposedPorts(5432)
                    .withDatabaseName("postgres")
                    .withUsername("postgres")
                    .withPassword("12345")
                    .start();
        }
    }
}