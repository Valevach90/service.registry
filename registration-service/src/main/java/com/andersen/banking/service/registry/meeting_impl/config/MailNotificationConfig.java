package com.andersen.banking.service.registry.meeting_impl.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailNotificationConfig {

    @Value("${notification.mail.host}")
    private String notificationMailHost;
    @Value("${notification.mail.port}")
    private int notificationMailPort;
    @Value("${notification.mail.sender.username}")
    private String notificationMailSenderUsername;
    @Value("${notification.mail.sender.password}")
    private String notificationMailSenderPassword;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(notificationMailHost);
        mailSender.setPort(notificationMailPort);

        mailSender.setUsername(notificationMailSenderUsername);
        mailSender.setPassword(notificationMailSenderPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
