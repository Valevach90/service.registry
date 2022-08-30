package com.andersen.banking.service.registry.meeting_impl.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("keycloak")
public class KeycloakProperties {
    String authServerUrl;
    String realm;
    String clientId;
    String clientSecret;
}
