package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.dto.KafkaTestDto;
import com.andersen.banking.meeting_impl.config.kafka.KafkaProperties;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KafkaTestController {

  private KafkaProperties kafkaProperties;

  private KafkaTemplate<String, KafkaTestDto> kafkaTemplate;

  @PostMapping("kafka")
  public String sendMessage(@RequestBody KafkaTestDto kafkaTestDto) {
    kafkaTemplate.send(kafkaProperties.getTopicName(), kafkaTestDto);
    return "Message is sent successfully";
  }
}
