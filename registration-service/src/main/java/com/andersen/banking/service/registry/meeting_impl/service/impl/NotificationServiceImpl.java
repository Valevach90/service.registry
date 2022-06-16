package com.andersen.banking.service.registry.meeting_impl.service.impl;

import com.andersen.banking.service.registry.meeting_db.entities.Notification;
import com.andersen.banking.service.registry.meeting_db.repositories.NotificationRepository;
import com.andersen.banking.service.registry.meeting_impl.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

import static com.andersen.banking.service.registry.meeting_impl.util.MailNotificationUtil.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    public JavaMailSender emailSender;

    private final NotificationRepository notificationRepository;

    @Value("${notification.mail.code.length}")
    private int NOTIFICATION_CODE_LENGTH;
    @Value("${notification.mail.code.valid.millis}")
    private int NOTIFICATION_CODE_VALID_MILLIS;

    @Override
    public void sendEmailNotification(String email) {


        String code = generateCode(NOTIFICATION_CODE_LENGTH);
        Timestamp time = new Timestamp(System.currentTimeMillis());

        Notification notification = new Notification();
        notification.setEmail(email);
        notification.setCode(code);
        notification.setTime(time);

        log.info("Creating notification: {}", notification);

        Optional<Notification> previousNotification = notificationRepository.findByEmail(email);
        if (previousNotification.isPresent()){
            notification.setId(previousNotification.get().getId());
        }
        notificationRepository.save(notification);

        log.info("Created notification: {}", notification);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Confirmation code");
        message.setText(code);

        log.info("Sending notification " + notification);

        this.emailSender.send(message);

        log.info("Sent notification " + notification);
    }

    @Override
    public Boolean confirmCodeReceivedByEmailNotification(String email, String code) {

        log.info("Confirmation whether code " + code + "was sent by email " + email);

        Optional<Notification> notification = notificationRepository.findByEmail(email);

        if (notification.isPresent()){
            Notification savedNotification = notification.get();
            Timestamp time = new Timestamp(System.currentTimeMillis() - NOTIFICATION_CODE_VALID_MILLIS);

            if (savedNotification.getCode().equals(code) && savedNotification.getTime().compareTo(time) > 0){
                return true;
            }
        }
        log.info("Code " + code + "was not sent by email " + email + "or time out");

        return false;
    }
}
