package com.andersen.banking.meeting_impl.service.impl;

import static com.andersen.banking.meeting_impl.util.MailNotificationUtil.BLOCKED;
import static com.andersen.banking.meeting_impl.util.MailNotificationUtil.createBlockingNotification;
import static com.andersen.banking.meeting_impl.util.MailNotificationUtil.createMessage;
import static com.andersen.banking.meeting_impl.util.MailNotificationUtil.createNotification;

import com.andersen.banking.meeting_db.entity.Notification;
import com.andersen.banking.meeting_db.repository.NotificationRepository;
import com.andersen.banking.meeting_impl.service.NotificationService;
import com.andersen.banking.meeting_impl.util.properties.NotificationMailProperties;
import java.sql.Timestamp;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMailProperties mail;
    @Autowired
    public JavaMailSender emailSender;

    @Override
    @Transactional
    public void sendEmailNotification(String email) {

        if (!isEmailAddressBlocked(email)) {
            Notification notification = createNotification(mail.getCode().getLength(), email);

            log.info("Creating notification: {}", notification);

            notificationRepository.save(notification);

            log.info("Created notification: {}", notification);

            SimpleMailMessage message = createMessage(notification);

            log.info("Sending notification message: {}", message);

            emailSender.send(message);

            log.info("Sent notification message: {} ", message);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean confirmCodeReceivedByEmailNotification(String email, String code) {

        log.info("Confirmation whether code {} was sent by email {}", code, email);

        Optional<Notification> notification = notificationRepository.findByEmail(email);

        if (notification.isPresent()) {
            Notification savedNotification = notification.get();
            Timestamp time = new Timestamp(
                    System.currentTimeMillis() - mail.getCode().getValid().getMillis());

            if (savedNotification.getCode().equals(code)
                    && savedNotification.getTime().compareTo(time) > 0) {
                return true;
            }
        }
        log.info("Code {} was not sent by email {} or time out", code, email);

        return false;
    }

    @Override
    @Transactional
    public void blockEmailAddress(String email) {

        Notification notification = createBlockingNotification(email);

        log.info("Creating blocking notification: {}", notification);

        notificationRepository.save(notification);

        log.info("Created blocking notification: {}", notification);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isEmailAddressBlocked(String email) {

        log.info("Checking if email address {} is blocked", email);

        Optional<Notification> notification = notificationRepository.findByEmail(email);

        if (notification.isPresent() && notification.get().getStatus().equals(BLOCKED)
                && notification.get().getTime().compareTo(
                new Timestamp(
                        System.currentTimeMillis() - mail.getBlocking().getTime().getMillis()))
                > 0) {
            log.info("Checked Email address {} is blocked", email);

            return true;
        } else {
            log.info("Checked Email address {} is not blocked", email);

            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Notification> getNotification(String email) {

        log.info("Find notification by email: {}", email);

        Optional<Notification> notification = notificationRepository.findByEmail(email);

        log.info("Found notification: {}", notification);

        return notification;
    }
}
