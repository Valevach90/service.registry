package com.andersen.banking.deposit_impl.kafka.utils;

import com.andersen.banking.deposit_api.dto.kafka.ResponseKafkaTransferMessage;
import com.andersen.banking.deposit_impl.config.KafkaConfigProperties;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@AllArgsConstructor
public class TransferServiceKafkaResponseConsumerImitationConfig {

    private KafkaConfigProperties kafkaConfigProperties;

    @Bean
    public ConsumerFactory<String, ResponseKafkaTransferMessage> consumerFactoryForTransferServiceImitation() {

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getBootstrapAddress());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProperties.getGroupId());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ResponseKafkaTransferMessage> kafkaListenerContainerFactoryForTransferServiceImitation() {
        ConcurrentKafkaListenerContainerFactory<String, ResponseKafkaTransferMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForTransferServiceImitation());
        factory.setBatchListener(false);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000L, 2L)));
        return factory;
    }
}
