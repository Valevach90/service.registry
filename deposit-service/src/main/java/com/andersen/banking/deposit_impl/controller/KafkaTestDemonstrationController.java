package com.andersen.banking.deposit_impl.controller;

import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import com.andersen.banking.deposit_impl.config.KafkaConfigProperties;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KafkaTestDemonstrationController {

    private static final String KEY_OF_RESPONSE = "response";
    private static final Integer PARTITION_OF_RESPONSE = 1;

    private KafkaConfigProperties kafkaProperties;

    private KafkaTemplate<String, RequestTransferKafkaMessage> kafkaTemplate;

    @PostMapping("kafka")
    public String sendMessage(@RequestBody RequestTransferKafkaMessage transferKafkaMessageDto) {

        kafkaTemplate.send(kafkaProperties.getTopicName(), PARTITION_OF_RESPONSE, KEY_OF_RESPONSE, transferKafkaMessageDto);
        return "Message is sent successfully";
    }
}
