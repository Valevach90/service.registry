package com.andersen.banking.service.payment.meeting_impl.config.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.kafka")
@Configuration
@Getter
@Setter
public class KafkaProperties {

  private String bootstrapAddress;

  private String groupId;

  private String topicName;

  private int maxRetryAttempts;
}
