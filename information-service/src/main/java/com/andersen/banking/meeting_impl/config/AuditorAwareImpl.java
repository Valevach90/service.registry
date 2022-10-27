package com.andersen.banking.meeting_impl.config;

import static com.andersen.banking.meeting_impl.util.JwtUtil.extractLoginFromToken;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public @NotNull Optional<String> getCurrentAuditor() {
        Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(extractLoginFromToken(token));
    }
}
