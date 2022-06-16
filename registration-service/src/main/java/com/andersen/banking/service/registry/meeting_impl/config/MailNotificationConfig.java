package com.andersen.banking.service.registry.meeting_impl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailNotificationConfig {

    @Value("${notification.mail.host}")
    private String NOTIFICATION_MAIL_HOST;
    @Value("${notification.mail.port}")
    private int NOTIFICATION_MAIL_PORT;
    @Value("${notification.mail.sender.username}")
    private String NOTIFICATION_MAIL_SENDER_USERNAME;
    @Value("${notification.mail.sender.password}")
    private String NOTIFICATION_MAIL_SENDER_PASSWORD;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(NOTIFICATION_MAIL_HOST);
        mailSender.setPort(NOTIFICATION_MAIL_PORT);

        mailSender.setUsername(NOTIFICATION_MAIL_SENDER_USERNAME);
        mailSender.setPassword(NOTIFICATION_MAIL_SENDER_PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
