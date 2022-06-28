package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.dto.KafkaTestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {

  private String topic;

  private KafkaTemplate<String, KafkaTestDto> kafkaTemplate;

  public KafkaTestController(@Value(value = "${tpd.topic-name}") String topic,
      KafkaTemplate<String, KafkaTestDto> kafkaTemplate) {
    this.topic = topic;
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping("kafka")
  public String sendMessage(@RequestBody KafkaTestDto kafkaTestDto) {
    kafkaTemplate.send(topic, kafkaTestDto);
    return "Message is sent successfully";
  }
}
