package com.andersen.banking.deposit_impl.kafka;

import com.andersen.banking.deposit_api.dto.messages.AccruedAmount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final Producer<String, AccruedAmount> producer;

    public Future<RecordMetadata> sendMessage(String key, AccruedAmount accruedAmount) {
        log.info("Producing message: {}", accruedAmount.toString());
        ProducerRecord<String, AccruedAmount> record = new ProducerRecord<>("topic_accrued_amount", key, accruedAmount);
        return producer.send(record);
    }
}