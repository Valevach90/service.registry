package com.andersen.banking.service.registry.meeting_impl.util;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;

public class MailNotificationUtil {

    public static String extractEmailFromToken(Jwt jwt){

        return jwt.getClaim("email").toString();
    }

    public static String generateCode(Integer length){

        String codeFormatLength = "%0" + length + "d";
        int randomNum = ThreadLocalRandom.current().nextInt(0, (int) pow(10, length));

        return String.format(codeFormatLength, randomNum);
    }
}