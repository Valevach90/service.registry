package com.andersen.banking.meeting_impl.util;

import org.springframework.security.oauth2.jwt.Jwt;

public class AuthServiceUtil {

    private AuthServiceUtil() {
    }

    public static String extractIdFromToken(Jwt jwt) {

        return jwt.getClaim("sub").toString();
    }

    public static String extractLoginFromToken(Jwt jwt) {

        return jwt.getClaim("preferred_username").toString();
    }

    public static String extractEmailFromToken(Jwt jwt) {

        return jwt.getClaim("email").toString();
    }

}
