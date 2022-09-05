package com.andersen.banking.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api-docs/**",
            "/*/api-docs",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/configuration/**",
            "/webjars/**",
            "/api/v1/**"
    };
    private static final String[] AUTH_ADMIN = {
            "/api/v1/products",
            "/api/v1/products/**"
    };
    private static final String[] AUTH_ROLES = {
            "ADMIN", "EMPLOYEE"
    };

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http,
            Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter
    ) {
        http.cors().and()
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, AUTH_ADMIN).hasAnyAuthority(AUTH_ROLES)
                .pathMatchers(HttpMethod.PUT, AUTH_ADMIN).hasAnyAuthority(AUTH_ROLES)
                .pathMatchers(HttpMethod.DELETE, AUTH_ADMIN).hasAnyAuthority(AUTH_ROLES)
                .pathMatchers(AUTH_WHITELIST).permitAll()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
        http.csrf().disable();
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
