package com.andersen.banking.deposit_impl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String REALM_ACCESS = "realm_access";
    private static final String RESOURCE_ACCESS = "resource_access";
    private static final String API_GATEWAY = "api-gateway";
    private static final String ROLES = "roles";


    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        List<GrantedAuthority> roles = new ArrayList<>();

        final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get(REALM_ACCESS);
        roles.addAll(getRoles(realmAccess));

        final Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get(RESOURCE_ACCESS);
        final Map<String, Object> gatewayAccess = (Map<String, Object>) resourceAccess.get(API_GATEWAY);

        if (resourceAccess.get(API_GATEWAY) != null) {
            roles.addAll(getRoles(gatewayAccess));
        }
        return roles;
    }

    private static List<SimpleGrantedAuthority> getRoles(Map<String, Object> gatewayAccess) {
        return ((List<String>) gatewayAccess.get(ROLES)).stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}