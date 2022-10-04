package com.andersen.banking.service.registry.meeting_impl.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakUrlUtil {


    public static String getUrlForCurrentUser(
            String keyCloakUrl, String realm, String userId) {
        return getUriComponentsBuilderAdminUser(keyCloakUrl, realm)
                .pathSegment(userId)
                .toUriString();
    }

    public static String getUrlForUser(String keyCloakUrl, String realm) {
        return getUriComponentsBuilderAdminUser(keyCloakUrl, realm)
                .toUriString();
    }

    public static String getUrlForGetUser(String keyCloakUrl, String realm, String username) {
        return getUriComponentsBuilderAdminUser(keyCloakUrl, realm)
                .queryParam("exact", "true")
                .queryParam("username", username)
                .toUriString();
    }

    public static String getUrlForGetUserEmail(String keyCloakUrl, String realm, String email) {
        return getUriComponentsBuilderAdminUser(keyCloakUrl, realm)
                .queryParam("exact", "true")
                .queryParam("email", email)
                .toUriString();
    }

    public static String getUrlForChangeRole(
            String keyCloakUrl, String realm, String userId, String clientId) {
        return getUriComponentsBuilderAdminUser(keyCloakUrl, realm)
                .pathSegment(userId)
                .pathSegment("role-mappings")
                .pathSegment("clients")
                .pathSegment(clientId)
                .toUriString();
    }

    public static String getUrlForResetPassword(String keyCloakUrl, String realm, String userId) {
        return getUriComponentsBuilderAdminUser(keyCloakUrl, realm)
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


    private static UriComponentsBuilder getUriComponentsBuilderAdminUser(String keyCloakUrl,
            String realm) {
        return UriComponentsBuilder.fromHttpUrl(keyCloakUrl)
                .pathSegment("auth")
                .pathSegment("admin")
                .pathSegment("realms")
                .pathSegment(realm)
                .pathSegment("users");
    }

    private static UriComponentsBuilder getUriComponentsBuilderOpenidConnect(String keyCloakUrl,
            String realm) {
        return UriComponentsBuilder.fromHttpUrl(keyCloakUrl)
                .pathSegment("auth")
                .pathSegment("realms")
                .pathSegment(realm)
                .pathSegment("protocol")
                .pathSegment("openid-connect");
    }
}
