package com.andersen.banking.service.payment.meeting_impl.util;

import com.andersen.banking.service.payment.meeting_impl.exception.PaymentServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CryptWithSHA {

    public static String getCrypt(String text) {

        StringBuilder sb = new StringBuilder();

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = text.getBytes();
            md.reset();
            byte[] digested = md.digest(bytes);

            for (byte b : digested) {
                sb.append(Integer.toHexString(0xff & b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            log.error("No crypt algorithm exception: {}", CryptWithSHA.class.getName());

            throw new PaymentServiceException("Text encryption problem.");
        }
    }
}
