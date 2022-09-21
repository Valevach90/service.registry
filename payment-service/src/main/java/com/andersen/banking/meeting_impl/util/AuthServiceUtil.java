package com.andersen.banking.meeting_impl.util;

import java.util.UUID;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthServiceUtil {

    private AuthServiceUtil() {
    }

    public static UUID extractIdFromToken(Jwt jwt) {

        String id = jwt.getClaim("sub").toString();
        return UUID.fromString(id);
    }

    public static String extractLoginFromToken(Jwt jwt) {

        return jwt.getClaim("preferred_username").toString();
    }

    public static String extractEmailFromToken(Jwt jwt) {

        return jwt.getClaim("email").toString();
    }

}
