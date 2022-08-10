package com.andersen.banking.service.registry.meeting_impl.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("keycloak.role")
public class KeycloakRoleProperties {
    private Unauthorized unauthorized;
    private User user;
    private Admin admin;

    @Getter
    @Setter
    public static class Unauthorized {
        private String id;
        private String name;
    }

    @Getter
    @Setter
    public static class User {
        private String id;
        private String name;
    }

    @Getter
    @Setter
    public static class Admin {
        private String id;
        private String name;
    }

}
