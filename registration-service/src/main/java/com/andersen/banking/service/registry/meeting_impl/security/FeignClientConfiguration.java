package com.andersen.banking.service.registry.meeting_impl.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class FeignClientConfiguration {

    private OAuth2AuthorizedClientManager authorizedClientManager;

    private OAuth2AccessToken getAccessToken() {
        var request = OAuth2AuthorizeRequest
                .withClientRegistrationId("keycloak") // <- Here you load your registered client
                .principal(SecurityContextHolder.getContext().getAuthentication())
                .build();
        return authorizedClientManager.authorize(request).getAccessToken();
    }
}
