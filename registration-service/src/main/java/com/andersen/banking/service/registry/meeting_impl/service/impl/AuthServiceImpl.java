package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_impl.exceptions.ValidationException;
import com.andersen.banking.service.registry.meeting_impl.service.AuthService;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakAdminProperties;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakClientProperties;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakRoleProperties;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakUriProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.*;
import static com.andersen.banking.service.registry.meeting_impl.util.ValidationUtil.*;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private KeycloakUriProperties uri;

    @Autowired
    private KeycloakRoleProperties role;

    @Autowired
    private KeycloakAdminProperties admin;

    @Autowired
    private KeycloakClientProperties clientProp;

    private WebClient client = WebClient.create();

    public void addRoleUnauthorized(String id) {
        String token = obtainAccessToken();

        log.debug("Add UNAUTHORIZED role to user, user id: " + id);
        
        String response = client.post()
                .uri(uri.getRealmUsers() +
                        id + uri.getRoleMapping() + clientProp.getGateway().getId())
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

        String token = obtainAccessToken();

        log.debug("Add USER role to user, user id: " + id);

        String responseDelete = client.method(HttpMethod.DELETE)
                .uri(uri.getRealmUsers()
                        + id + uri.getRoleMapping() + clientProp.getGateway().getId())
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(prepareRoleInJson(
                        role.getUnauthorized().getId(), role.getUnauthorized().getName())))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String response = client.post()
                .uri(uri.getRealmUsers()
                        + id + uri.getRoleMapping() + clientProp.getGateway().getId())
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(prepareRoleInJson(role.getUser().getId(), role.getUser().getName())))
                .retrieve()
                .bodyToMono(String.class)
                .block();



        log.debug("Add USER role to user success, user id: " + id);
    }

    public void addRoleAdmin(String id) {

        String token = obtainAccessToken();

        log.debug("Add ADMIN role to user, user id: " + id);

        String response = client.post()
                .uri(uri.getRealmUsers()
                        + id + uri.getRoleMapping() + clientProp.getGateway().getId())
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(prepareRoleInJson(role.getAdmin().getId(), role.getAdmin().getName())))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Add ADMIN role to user success, user id: " + id);
    }

    public void addUser(String userInJson) {

        String token = obtainAccessToken();

        log.debug("Add user: " + userInJson);

        String response = client.post()
                .uri(uri.getAdd())
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(userInJson))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Add user success");
    }

    @Override
    public void resetPassword(String id, String newPassword) {

        String token = obtainAccessToken();

        log.debug("Validating new password: password {}", newPassword);
        if (isPasswordValid(newPassword)){

            log.debug("Setting new password: user id {}, password {}", id, newPassword);

            String response = client.put()
                    .uri(uri.getRealmUsers() + id + uri.getPasswordReset())
                    .headers(header -> header.setBearerAuth(token))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(preparePasswordInJson(newPassword)))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.debug("New password set: user id {}, password {}", id, newPassword);
        } else {
            throw new ValidationException(String.format("Password does not meet policy requirements: password: {}", newPassword));
        }
    }

    private String obtainAccessToken() {

        log.debug("Get access token, admin username" + admin.getUsername());

        String response = client.post()
                .uri(uri.getTokensObtain())
                .body(BodyInserters.fromFormData(prepareBodyToGetAccessToken(
                        admin.getUsername(), admin.getPassword(), admin.getGrantType(), admin.getClientId())))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Return access token, admin username" + admin.getUsername());
        return extractAccessTokenFromJson(response);
    }
}
