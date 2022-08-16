package com.andersen.banking.deposit_impl.kafka.utils;

import com.andersen.banking.deposit_api.dto.kafka.ResponseKafkaTransferMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class TransferServiceKafkaConsumerImitation {

    private CountDownLatch latch = new CountDownLatch(1);
    private ResponseKafkaTransferMessage message;

    @KafkaListener(topics = "${spring.kafka.topicTransferResponse}", groupId = "${spring.kafka.groupId}")
    public void receive(@Payload ResponseKafkaTransferMessage message) {

        this.message = message;

        latch.countDown();
    }

    public ResponseKafkaTransferMessage getMessage(){
        return message;
    }

    public CountDownLatch getLatch() { return latch; }
}
