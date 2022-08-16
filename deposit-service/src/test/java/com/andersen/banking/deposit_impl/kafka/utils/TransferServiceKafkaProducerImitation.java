package com.andersen.banking.deposit_impl.kafka.utils;

import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransferServiceKafkaProducerImitation {

    @Autowired
    private KafkaTemplate<String, RequestTransferKafkaMessage> kafkaTemplate;

    public TransferServiceKafkaProducerImitation() {
    }

    public void send(String topic, RequestTransferKafkaMessage message) {
        kafkaTemplate.send(topic, message);
    }
}
