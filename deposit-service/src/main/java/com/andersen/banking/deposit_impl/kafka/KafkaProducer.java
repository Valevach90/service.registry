package com.andersen.banking.deposit_impl.kafka;

import com.andersen.banking.deposit_api.dto.messages.AccruedAmount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, AccruedAmount> kafkaTemplate;

    public void sendMessage(String topicName, AccruedAmount accruedAmount) {
        log.info("Producing message: {}", accruedAmount.toString());
        kafkaTemplate.send(topicName, "accruedAmount", accruedAmount)
                .addCallback(
                        result -> log.info("Message sent to topic: {}", topicName),
                        ex -> log.error("Failed to send message", ex)
                );
    }
}
