package com.andersen.banking.meeting_impl.kafka.listener;

import com.andersen.banking.meeting_api.dto.message.TransferKafkaDeposit;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class KafkaListenerDeposit {

    private TransferMoneyService transferMoneyService;
    private static final String TOPIC = "sendAccrueAmount";

    @KafkaListener(topics = TOPIC, containerFactory = "kafkaListenerContainerFactory")
    public void receive(@Payload List<TransferKafkaDeposit> messages) {
        messages.forEach(transfer -> transferMoneyService.createTransferForAccruedAmount(transfer));
    }
}

