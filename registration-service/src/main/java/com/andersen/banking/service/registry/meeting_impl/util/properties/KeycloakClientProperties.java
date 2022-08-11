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

    private Gateway gateway;
    private GatewayUserManager gatewayUserManager;

    @Getter
    @Setter
    public static class Gateway {
        private String id;
        private String name;
    }

    @Getter
    @Setter
    public static class GatewayUserManager {
        private String id;
        private String name;
    }
}
