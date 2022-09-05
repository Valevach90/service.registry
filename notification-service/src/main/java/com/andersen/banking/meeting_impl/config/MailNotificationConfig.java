package com.andersen.banking.meeting_impl.config;

import com.andersen.banking.meeting_impl.util.properties.NotificationMailProperties;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@RequiredArgsConstructor
public class MailNotificationConfig {

    private final NotificationMailProperties mail;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mail.getHost());
        mailSender.setPort(mail.getPort());

        mailSender.setUsername(mail.getSender().getUsername());
        mailSender.setPassword(mail.getSender().getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
