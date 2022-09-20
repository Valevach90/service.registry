package com.andersen.banking.meeting_impl.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaConfigProperties {

    private String bootstrapAddress;

    private String groupId;

    private String topicTransferRequest;

    private String topicTransferResponse;

    private int maxRetryAttempts;
}
