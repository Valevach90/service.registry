package com.andersen.banking.meeting_impl.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import reactivefeign.client.ReactiveHttpRequest;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Mono;

@Component
public class FeignClientInterceptor implements ReactiveHttpRequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    @Override
    public Mono<ReactiveHttpRequest> apply(ReactiveHttpRequest reactiveHttpRequest) {
        reactiveHttpRequest.headers()
                .computeIfPresent(AUTHORIZATION_HEADER, (k, v) -> {
                    String s = v.get(0);
                    return List.of(String.format("%s %s", TOKEN_TYPE, s));
                });
        return Mono.just(reactiveHttpRequest);
    }
}
