package com.andersen.banking.service.payment.meeting_impl.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CryptWithSHA {

    private static MessageDigest md;

    public static String getCrypt(String text) {

        try {

            md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = text.getBytes();
            md.reset();
            byte[] digested = md.digest(bytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException ex) {

            log.error("No crypt algorithm exception: {}", CryptWithSHA.class.getName());

        }
        return null;
    }

}
