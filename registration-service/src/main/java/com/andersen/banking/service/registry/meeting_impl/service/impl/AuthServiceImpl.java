package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_impl.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${keycloak.uri.tokens.obtain}")
    private String URI_TOKENS_OBTAIN;
    @Value("${keycloak.uri.users.add}")
    private String URI_USERS_ADD;
    @Value("${keycloak.uri.roles.add.to.user}")
    private String URI_ROLE_ADD_TO_USER;
    @Value("${keycloak.uri.roles.add.to.client}")
    private String URI_ROLE_ADD_TO_CLIENT;
    @Value("${keycloak.uri.users}")
    private String uriUsers;
    @Value("${keycloak.uri.password.reset}")
    private String uriPasswordReset;
    @Value("${keycloak.role.id.unauthorized}")
    private String roleIdUnauthorized;
    @Value("${keycloak.role.name.unauthorized}")
    private String roleNameUnauthorized;
    @Value("${keycloak.role.id.user}")
    private String roleIdUser;
    @Value("${keycloak.role.name.user}")
    private String roleNameUser;
    @Value("${keycloak.role.id.admin}")
    private String roleIdAdmin;
    @Value("${keycloak.role.name.admin}")
    private String roleNameAdmin;

    @Value("${keycloak.client.id.gateway}")
    private String clientId;

    @Value("${keycloak.admin.username}")
    private String adminName;
    @Value("${keycloak.admin.password}")
    private String adminPassword;
    @Value("${keycloak.admin.grant.type}")
    private String adminGrantType;
    @Value("${keycloak.admin.client.id}")
    private String adminClientId;

    WebClient client = WebClient.create();

    public void addRoleUnauthorized(String id){

        String token = obtainAccessToken();

        log.debug("Add UNAUTHORIZED role to user, user id: " + id);

        String response = client.post()
                .uri(URI_ROLE_ADD_TO_USER + id + URI_ROLE_ADD_TO_CLIENT + clientId)
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(prepareRoleInJson(roleIdUnauthorized, roleNameUnauthorized)))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Add UNAUTHORIZED role to user success, user id: " + id);
    }

    public void addRoleUser(String id){

        String token = obtainAccessToken();

        log.debug("Add USER role to user, user id: " + id);

        String response = client.post()
                .uri(URI_ROLE_ADD_TO_USER + id + URI_ROLE_ADD_TO_CLIENT + clientId)
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(prepareRoleInJson(roleIdUser, roleNameUser)))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Add USER role to user success, user id: " + id);
    }

    public void addRoleAdmin(String id){

        String token = obtainAccessToken();

        log.debug("Add ADMIN role to user, user id: " + id);

        String response = client.post()
                .uri(URI_ROLE_ADD_TO_USER + id + URI_ROLE_ADD_TO_CLIENT + clientId)
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(prepareRoleInJson(roleIdAdmin, roleNameAdmin)))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Add ADMIN role to user success, user id: " + id);
    }

    public void addUser(String userInJson){

        String token = obtainAccessToken();

        log.debug("Add user: " + userInJson);

        String response = client.post()
                .uri(URI_USERS_ADD)
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

        log.debug("Setting new password: user id {}, password {}", id, newPassword);

        String response = client.put()
                .uri(uriUsers + id + uriPasswordReset)
                .headers(header -> header.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(preparePasswordInJson(newPassword)))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("New password set: user id {}, password {}", id, newPassword);

    }

    private String obtainAccessToken() {

        log.debug("Get access token, admin username" + adminName);

        String response = client.post()
                .uri(URI_TOKENS_OBTAIN)
                .body(BodyInserters.fromFormData(prepareBodyToGetAccessToken(adminName, adminPassword, adminGrantType,adminClientId)))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Return access token, admin username" + adminName);
        return extractAccessTokenFromJson(response);
    }
}
