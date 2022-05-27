package com.andersen.banking.service.registry.meeting_impl.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        List<GrantedAuthority> roles = new ArrayList<>();

        final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        roles.addAll(
                ((List<String>)realmAccess.get("roles")).stream()
                        .map(roleName -> "ROLE_" + roleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );

        final Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
        final Map<String, Object> gatewayAccess = (Map<String, Object>) resourceAccess.get("api-gateway");

        if (resourceAccess.get("api-gateway") != null) {
            roles.addAll(
                    ((List<String>) gatewayAccess.get("roles")).stream()
                            .map(roleName -> "ROLE_" + roleName)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
        }
        return roles;
    }
}

