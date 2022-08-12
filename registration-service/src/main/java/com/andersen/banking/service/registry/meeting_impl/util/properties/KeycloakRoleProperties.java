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
    private Role unauthorized;
    private Role user;
    private Role admin;

    @Getter
    @Setter
    public static class Role {
        private String id;
        private String name;
    }
}
