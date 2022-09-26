package com.andersen.banking.meeting_impl.kafka.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaProperties {

    private String bootstrapAddress;

    private String groupId;

    private int maxRetryAttempts;

    private String paymentTransferRequestTopicName;

    private String depositTransferRequestTopicName;

    private String transferResponseTopicName;

    private String paymentDepositTransferRequestTopicName;

    private String depositPaymentTransferRequestTopicName;
}
