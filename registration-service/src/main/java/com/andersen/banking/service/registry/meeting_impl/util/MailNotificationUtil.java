package com.andersen.banking.service.registry.meeting_impl.util;

import static java.lang.Math.pow;

import com.andersen.banking.service.registry.meeting_db.entities.Notification;
import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.oauth2.jwt.Jwt;

public class MailNotificationUtil {

    public static final String BLOCKED = "blocked";
    public static final String SENT = "sent";

    private MailNotificationUtil(){
    }

    public static String extractEmailFromToken(Jwt jwt){

        return jwt.getClaim("email").toString();
    }

    public static String generateCode(Integer length){

        String codeFormatLength = "%0" + length + "d";
        int randomNum = ThreadLocalRandom.current().nextInt(0, (int) pow(10, length));

        return String.format(codeFormatLength, randomNum);
    }

    public static Notification createNotification(int notificationCodeLength, String email){

        String code = generateCode(notificationCodeLength);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Notification notification = new Notification(email, code, time, SENT);

        return notification;
    }

    public static Notification createBlockingNotification(String email){

        Notification notification = new Notification(email, "", new Timestamp(System.currentTimeMillis()), BLOCKED);

        return notification;
    }

    public static SimpleMailMessage createMessage(Notification notification){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(notification.getEmail());
        message.setSubject("Confirmation code");
        message.setText(notification.getCode());

        return message;
    }
}
