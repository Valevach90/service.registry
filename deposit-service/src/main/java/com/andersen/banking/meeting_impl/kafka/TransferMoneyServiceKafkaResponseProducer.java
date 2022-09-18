package com.andersen.banking.meeting_impl.kafka;

import com.andersen.banking.meeting_impl.kafka.message.ResponseKafkaTransferMessage;
import com.andersen.banking.meeting_impl.config.KafkaConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyServiceKafkaResponseProducer {

    private final KafkaConfigProperties kafkaProperties;

    private final KafkaTemplate<String, ResponseKafkaTransferMessage> kafkaTemplate;

    public void sendResponse(ResponseKafkaTransferMessage response) {

        log.info("Sending response message Transfer service: {}", response);
        kafkaTemplate.send(kafkaProperties.getTopicTransferResponse(), response);
    }
}
