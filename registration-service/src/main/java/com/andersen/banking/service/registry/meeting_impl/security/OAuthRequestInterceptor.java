package com.andersen.banking.service.registry.meeting_impl.security;

import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;

import java.util.Optional;

import static org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor.BEARER;

@Slf4j
public class OAuthRequestInterceptor implements RequestInterceptor {

    OAuth2AuthorizedClientManager clientManager;

    public OAuthRequestInterceptor(OAuth2AuthorizedClientManager clientManager) {
        this.clientManager = clientManager;
    }

    private String getAccessToken() {
        return "s";
        /*OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest.withClientRegistrationId("keycloak")
                .principal(SecurityContextHolder.getContext().getAuthentication())
                .build();
        return Optional.ofNullable(authorizedClientManager)
                .map(clientManager -> clientManager.authorize(request))
                .map(OAuth2AuthorizedClient::getAccessToken)
                .map(AbstractOAuth2Token::getTokenValue)
                .orElseThrow(OAuth2AccessTokenRetrievalException::failureToRetrieve);*/
    }

    @Override
    public void apply(RequestTemplate template) {
        log.debug("FeignClientInterceptor -> apply CALLED");
        String token = getAccessToken();
        if (token != null) {
            String bearerString = String.format("%s %s", BEARER, token);
            template.header(HttpHeaders.AUTHORIZATION, bearerString);
            log.debug("set the template header to this bearer string: {}", bearerString);
        } else {
            log.error("No bearer string.");
        }
    }
}
