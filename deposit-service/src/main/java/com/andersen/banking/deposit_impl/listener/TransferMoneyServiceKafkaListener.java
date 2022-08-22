package com.andersen.banking.deposit_impl.listener;

import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import com.andersen.banking.deposit_impl.config.KafkaConfigProperties;
import com.andersen.banking.deposit_impl.mapping.TransferMapper;
import com.andersen.banking.deposit_impl.service.DepositService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class TransferMoneyServiceKafkaListener {

    private KafkaConfigProperties kafkaProperties;
    @Getter
    @Setter
    private DepositService depositService;

    @KafkaListener(topics = "${spring.kafka.topicTransferRequest}", groupId = "${spring.kafka.groupId}")
    public void listenTransferMoneyService(@Payload RequestTransferKafkaMessage message) {

        depositService.makeTransfer(message);
    }


}
