package com.andersen.banking.service.registry.meeting_impl.util;


import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KeycloakUrlUtil {

    private KeycloakUrlUtil() {
    }

    public static String getUrlForChangeRole(
            String keyCloakUrl, String realm, String userId, String clientId) {
        return getUriComponentsBuilderUser(keyCloakUrl, realm)
                .pathSegment(userId)
                .pathSegment("role-mappings")
                .pathSegment("clients")
                .pathSegment(clientId)
                .toUriString();
    }

    public static String getUrlForAddUser(String keyCloakUrl, String realm) {
        return getUriComponentsBuilderUser(keyCloakUrl, realm)
                .toUriString();
    }

    public static String getUrlForResetPassword(String keyCloakUrl, String realm, String userId) {
        return getUriComponentsBuilderUser(keyCloakUrl, realm)
                .pathSegment(userId)
                .pathSegment("reset-password")
                .toUriString();
    }

    public static String getUrlForToken(String keyCloakUrl, String realm) {
        return getUriComponentsBuilderOpenidConnect(keyCloakUrl, realm)
                .pathSegment("token")
                .toUriString();
    }

    public static String getUriForLogout(String keyCloakUrl, String realm) {
        return getUriComponentsBuilderOpenidConnect(keyCloakUrl, realm)
                .pathSegment("logout")
                .toUriString();
    }

    public static String getUriJwks(String keyCloakUrl, String realm) {
        return getUriComponentsBuilderOpenidConnect(keyCloakUrl, realm)
                .pathSegment("certs")
                .toUriString();
    }

    private static UriComponentsBuilder getUriComponentsBuilderUser(String keyCloakUrl, String realm) {
        return UriComponentsBuilder.fromHttpUrl(keyCloakUrl)
                .pathSegment("auth")
                .pathSegment("admin")
                .pathSegment("realms")
                .pathSegment(realm)
                .pathSegment("users");
    }

    private static UriComponentsBuilder getUriComponentsBuilderOpenidConnect(String keyCloakUrl, String realm) {
        return UriComponentsBuilder.fromHttpUrl(keyCloakUrl)
                .pathSegment("auth")
                .pathSegment("realms")
                .pathSegment(realm)
                .pathSegment("protocol")
                .pathSegment("openid-connect");
    }
}
