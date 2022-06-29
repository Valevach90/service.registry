package com.andersen.banking.service.payment.meeting_impl.listener.kafka;

import com.andersen.banking.service.payment.meeting_api.dto.KafkaTestDto;
import com.andersen.banking.service.payment.meeting_impl.config.kafka.KafkaProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TestTopicListener {

  private KafkaProperties kafkaProperties;

  @KafkaListener(topics = "${spring.kafka.topicName}", groupId = "${spring.kafka.groupId}")
  public void listenGroupFoo(@Payload List<KafkaTestDto> messages,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
      @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
    messages.forEach(x -> System.out.println("Received Message in group : " + x));
  }
}
