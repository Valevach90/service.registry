package com.andersen.banking.service.registry.meeting_impl.service.impl;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.preparePasswordInJson;
import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.prepareRoleInJson;
import static com.andersen.banking.service.registry.meeting_impl.util.ValidationUtil.isPasswordValid;

import com.andersen.banking.service.registry.meeting_api.dto.TokenDto;
import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;
import com.andersen.banking.service.registry.meeting_impl.service.AdminService;
import com.andersen.banking.service.registry.meeting_impl.service.AuthService;
import com.andersen.banking.service.registry.meeting_impl.util.KeycloakUrlUtil;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakClientProperties;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakProperties;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakRoleProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KeycloakProperties keycloak;

    private final KeycloakRoleProperties role;

    private final AdminService adminService;

    private final KeycloakClientProperties clientProp;

    private final WebClient client = WebClient.create();

    public void addRoleUnauthorized(String id) {
        String token = adminService.obtainAccessToken();

        log.debug("Add UNAUTHORIZED role to user, user id: " + id);

        String response = client.post()
                .uri(KeycloakUrlUtil.getUrlForChangeRole(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        id,
                        clientProp.getGateway().getId()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(prepareRoleInJson(
                        role.getUnauthorized().getId(), role.getUnauthorized().getName())))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Add UNAUTHORIZED role to user success, user id: " + id);
    }

    public void addRoleUser(String id) {

        String token = adminService.obtainAccessToken();

        log.debug("Add USER role to user, user id: " + id);

        String responseDelete = client.method(HttpMethod.DELETE)
                .uri(KeycloakUrlUtil.getUrlForChangeRole(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        id,
                        clientProp.getGateway().getId()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(prepareRoleInJson(
                        role.getUnauthorized().getId(), role.getUnauthorized().getName())))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String response = client.post()
                .uri(KeycloakUrlUtil.getUrlForChangeRole(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        id,
                        clientProp.getGateway().getId()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        prepareRoleInJson(role.getUser().getId(), role.getUser().getName())))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Add USER role to user success, user id: " + id);
    }

    public void addRoleAdmin(String id) {

        String token = adminService.obtainAccessToken();

        log.debug("Add ADMIN role to user, user id: " + id);

        String response = client.post()
                .uri(KeycloakUrlUtil.getUrlForChangeRole(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm(),
                        id,
                        clientProp.getGateway().getId()
                ))
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        prepareRoleInJson(role.getAdmin().getId(), role.getAdmin().getName())))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Add ADMIN role to user success, user id: " + id);
    }

    @Override
    public void resetPassword(String id, String newPassword) {

        String token = adminService.obtainAccessToken();

        log.debug("Setting new password: user id {}, password {}", id, newPassword);
        if (isPasswordValid(newPassword)) {
            try {
                String response = client.put()
                        .uri(KeycloakUrlUtil.getUrlForResetPassword(
                                keycloak.getAuthServerUrl(),
                                keycloak.getRealm(),
                                id
                        ))
                        .headers(header -> header.setBearerAuth(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(preparePasswordInJson(newPassword)))
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
            } catch (WebClientResponseException e) {
                throw new ValidationException(
                        String.format("New password %s is recently used", newPassword));
            }
            log.debug("New password set: user id {}, password {}", id, newPassword);
        } else {
            throw new ValidationException(
                    String.format("New password %s does not meet policy requirements",
                            newPassword));
        }
    }

    @Override
    public TokenDto refreshToken(String refreshToken) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "refresh_token");
        parameters.add("client_id", keycloak.getClientIdRefresh());
        parameters.add("refresh_token", refreshToken);

        return client.post()
                .uri(KeycloakUrlUtil.getUrlForToken(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm()
                ))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(parameters))
                .retrieve()
                .bodyToMono(TokenDto.class)
                .block();
    }

    @Override
    public void logoutUser(String refreshToken) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", keycloak.getClientId());
        parameters.add("client_secret", keycloak.getClientSecret());
        parameters.add("refresh_token", refreshToken);

        String response = client.post()
                .uri(KeycloakUrlUtil.getUriForLogout(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm()
                ))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(parameters))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


}
