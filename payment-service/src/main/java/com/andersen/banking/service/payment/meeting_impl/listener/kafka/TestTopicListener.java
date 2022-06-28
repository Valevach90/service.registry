package com.andersen.banking.service.payment.meeting_impl.listener.kafka;

import com.andersen.banking.service.payment.meeting_api.dto.KafkaTestDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestTopicListener {

  @KafkaListener(topics = "${tpd.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
  public void listenGroupFoo(KafkaTestDto kafkaTestDto) {
    System.out.println("Received Message in group foo: " + kafkaTestDto);
  }
}
