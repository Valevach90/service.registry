package com.andersen.banking.deposit_impl.listener;

import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import com.andersen.banking.deposit_impl.config.KafkaConfigProperties;
import com.andersen.banking.deposit_impl.mapping.TransferMapper;
import com.andersen.banking.deposit_impl.service.DepositService;

import lombok.AllArgsConstructor;
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

    private static final String PARTITION_OF_REQUEST = "1";

    private KafkaConfigProperties kafkaProperties;
    private DepositService depositService;

    private TransferMapper transferMapper;

    @KafkaListener(groupId = "${spring.kafka.groupId}", topicPartitions =
                                                        { @TopicPartition(topic = "${spring.kafka.topicName}", partitions = PARTITION_OF_REQUEST)})
    public void listenTransferMoneyService(@Payload RequestTransferKafkaMessage message) {

        depositService.makeTransfer(transferMapper.toTransfer(message));
    }
}
