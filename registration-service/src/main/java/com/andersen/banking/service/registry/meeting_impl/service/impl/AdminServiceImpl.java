package com.andersen.banking.service.registry.meeting_impl.service.impl;

import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.extractAccessTokenFromJson;
import static com.andersen.banking.service.registry.meeting_impl.util.AuthServiceUtil.prepareBodyToGetAccessToken;

import com.andersen.banking.service.registry.meeting_impl.service.AdminService;
import com.andersen.banking.service.registry.meeting_impl.util.KeycloakUrlUtil;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakAdminProperties;
import com.andersen.banking.service.registry.meeting_impl.util.properties.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final KeycloakProperties keycloak;

    private final WebClient client = WebClient.create();

    private final KeycloakAdminProperties admin;

    public String obtainAccessToken() {

        log.debug("Get access token, admin username" + admin.getUsername());

        String response = client.post()
                .uri(KeycloakUrlUtil.getUrlForToken(
                        keycloak.getAuthServerUrl(),
                        keycloak.getRealm()
                ))
                .body(BodyInserters.fromFormData(prepareBodyToGetAccessToken(
                        admin.getUsername(), admin.getPassword(), admin.getGrantType(),
                        admin.getClientId())))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.debug("Return access token, admin username" + admin.getUsername());
        return extractAccessTokenFromJson(response);
    }
}
