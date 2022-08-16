package com.andersen.banking.service.registry.meeting_impl.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("keycloak.admin")
public class KeycloakAdminProperties {
    private String username;
    private String password;
    private String grantType;
    private String clientId;
}
