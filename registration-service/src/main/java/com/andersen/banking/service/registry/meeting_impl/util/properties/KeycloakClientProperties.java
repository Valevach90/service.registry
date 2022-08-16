package com.andersen.banking.service.registry.meeting_impl.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("keycloak.client")
public class KeycloakClientProperties {

    private GatewayProp gateway;
    private GatewayProp gatewayUserManager;

    @Getter
    @Setter
    public static class GatewayProp {
        private String id;
        private String name;
    }
}
