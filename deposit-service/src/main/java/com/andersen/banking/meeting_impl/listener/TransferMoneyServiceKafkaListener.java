package com.andersen.banking.meeting_impl.listener;

import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.config.KafkaConfigProperties;
import com.andersen.banking.meeting_impl.service.DepositService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransferMoneyServiceKafkaListener {

    private KafkaConfigProperties kafkaProperties;
    @Getter
    @Setter
    private DepositService depositService;

    @KafkaListener(topics = "${spring.kafka.topicTransferRequest}", groupId = "${spring.kafka.groupId}")
    public void listenTransferMoneyService(@Payload RequestKafkaTransferMessage message) {

        depositService.makeTransfer(message);
    }


}
