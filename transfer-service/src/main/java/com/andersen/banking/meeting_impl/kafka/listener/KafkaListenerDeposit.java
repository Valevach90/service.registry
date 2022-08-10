package com.andersen.banking.meeting_impl.kafka.listener;

import com.andersen.banking.meeting_api.dto.message.TransferKafkaDeposit;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class KafkaListenerDeposit {

    private TransferMoneyService transferMoneyService;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "sendAccrueAmount"))
    public void listenGroupFooForTransferFromDeposit(@Payload List<TransferKafkaDeposit> messages,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        messages.forEach(x -> transferMoneyService.createTransferForAccruedAmount(x.getMessage()));
    }
}
