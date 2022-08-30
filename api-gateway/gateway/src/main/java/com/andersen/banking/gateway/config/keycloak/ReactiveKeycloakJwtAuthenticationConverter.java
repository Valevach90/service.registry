package com.andersen.banking.gateway.config.keycloak;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Optional;

public final class ReactiveKeycloakJwtAuthenticationConverter
        implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    private static final String USERNAME_CLAIM = "preferred_username";
    private final Converter<Jwt, Flux<GrantedAuthority>> jwtGrantedAuthoritiesConverter;

    public ReactiveKeycloakJwtAuthenticationConverter(
            Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter
    ) {
        Assert.notNull(jwtGrantedAuthoritiesConverter, "jwtGrantedAuthoritiesConverter cannot be null");
        this.jwtGrantedAuthoritiesConverter = new ReactiveJwtGrantedAuthoritiesConverterAdapter(
                jwtGrantedAuthoritiesConverter);
    }

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
        String username = extractUsername(jwt).orElse("");
        return this.jwtGrantedAuthoritiesConverter.convert(jwt)
                .collectList()
                .map(authorities -> new JwtAuthenticationToken(jwt, authorities, username));
    }

    private Optional<String> extractUsername(Jwt jwt) {
        return Optional.of(jwt.getClaimAsString(USERNAME_CLAIM));
    }
}
