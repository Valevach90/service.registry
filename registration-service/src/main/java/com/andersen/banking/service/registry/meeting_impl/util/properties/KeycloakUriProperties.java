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
    private String add;
    private String realmUsers;
    private String roleMapping;
    private String jwks;
    private String passwordReset;
}
