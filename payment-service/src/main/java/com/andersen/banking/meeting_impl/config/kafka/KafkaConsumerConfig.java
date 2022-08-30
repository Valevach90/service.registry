package com.andersen.banking.meeting_impl.config.kafka;

import com.andersen.banking.meeting_api.dto.KafkaTestDto;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@EnableKafka
@Configuration
@AllArgsConstructor
@Slf4j
public class KafkaConsumerConfig {

  private KafkaProperties kafkaProperties;

  @Bean
  public ConsumerFactory<String, KafkaTestDto> consumerFactory() {

    Map<String, Object> config = new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapAddress());
    config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    return new DefaultKafkaConsumerFactory<>(config);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, KafkaTestDto> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, KafkaTestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setRetryTemplate(retryTemplate());
    factory.setErrorHandler(new SeekToCurrentErrorHandler());
    factory.setBatchListener(false);
    return factory;
  }

  private RetryTemplate retryTemplate() {
    RetryTemplate retryTemplate = new RetryTemplate();
    retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
    return retryTemplate;
  }

  private SimpleRetryPolicy getSimpleRetryPolicy() {
    Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
    exceptionMap.put(RuntimeException.class, true);
    return new SimpleRetryPolicy(kafkaProperties.getMaxRetryAttempts(), exceptionMap, true);
  }
}
