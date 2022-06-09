package com.andersen.banking.service.registry.meeting_impl.security;

import com.andersen.banking.service.registry.meeting_impl.util.KeycloakRealmRoleConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

/**
 * Configuration of Security.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${keycloak.uri.jwks}")
    private String JWKS_URI;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                //.antMatchers("/api/v1/auth").authenticated()
                //.antMatchers("/api/v1/users").hasRole("ADMIN")
                //.antMatchers("/api/v1/passport").hasRole("ADMIN")
                //.antMatchers("/api/v1/addresses").hasRole("ADMIN")
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/swagger-ui/**", "/api-docs**").permitAll()
                .anyRequest().authenticated()).oauth2ResourceServer(
                oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                )
        );
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtConverter;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.JWKS_URI).build();
    }
}