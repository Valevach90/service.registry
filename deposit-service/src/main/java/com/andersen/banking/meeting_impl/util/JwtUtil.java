package com.andersen.banking.meeting_impl.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtil {

    public static String extractIdFromToken(Jwt jwt){

        return jwt.getClaim("sub").toString();
    }

}
