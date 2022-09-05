package com.andersen.banking.meeting_impl.util;

import static java.lang.Math.pow;

import com.andersen.banking.meeting_db.entity.RegistrationNotification;
import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.oauth2.jwt.Jwt;

public class MailNotificationUtil {

    public static final String BLOCKED = "blocked";
    public static final String SENT = "sent";

    private MailNotificationUtil() {
    }

    public static String extractEmailFromToken(Jwt jwt) {

        return jwt.getClaim("email").toString();
    }

    public static String generateCode(Integer length) {

        String codeFormatLength = "%0" + length + "d";
        int randomNum = ThreadLocalRandom.current().nextInt(0, (int) pow(10, length));

        return String.format(codeFormatLength, randomNum);
    }

    public static RegistrationNotification createNotification(int notificationCodeLength, String email) {

        String code = generateCode(notificationCodeLength);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        RegistrationNotification registrationNotification = new RegistrationNotification(email, code, time, SENT);

        return registrationNotification;
    }

    public static RegistrationNotification createBlockingNotification(String email) {

        RegistrationNotification registrationNotification = new RegistrationNotification(email, "",
                new Timestamp(System.currentTimeMillis()), BLOCKED);

        return registrationNotification;
    }

    public static SimpleMailMessage createMessage(RegistrationNotification registrationNotification) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(registrationNotification.getEmail());
        message.setSubject("Confirmation code");
        message.setText(registrationNotification.getCode());

        return message;
    }
}
