package com.andersen.banking.deposit_impl.controller;

import com.andersen.banking.deposit_api.dto.kafka.TransferKafkaMessageDto;
import com.andersen.banking.deposit_impl.config.KafkaConfigProperties;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KafkaTestDemonstrationController {
    private KafkaConfigProperties kafkaProperties;

    private KafkaTemplate<String, TransferKafkaMessageDto> kafkaTemplate;

    @PostMapping("kafka")
    public String sendMessage(@RequestBody TransferKafkaMessageDto transferKafkaMessageDto) {
        kafkaTemplate.send(kafkaProperties.getTopicName(), transferKafkaMessageDto);
        return "Message is sent successfully";

    }
}
