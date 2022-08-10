package com.andersen.banking.service.registry.meeting_impl.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("keycloak.uri")
public class KeycloakUriProperties {
    private String tokensObtain;
    private Users users;
    private Roles roles;
    private String jwks;
    private String passwordReset;

    @Getter
    @Setter
    public static class Users {
        private String add;
    }

    @Getter
    @Setter
    public static class Roles {
        private String addToUser;
        private String addToClient;
    }
}
