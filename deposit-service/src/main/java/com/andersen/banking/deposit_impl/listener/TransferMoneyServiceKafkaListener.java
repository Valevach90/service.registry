package com.andersen.banking.deposit_impl.listener;

import com.andersen.banking.deposit_api.dto.kafka.TransferKafkaMessageDto;
import com.andersen.banking.deposit_impl.config.KafkaConfigProperties;
import com.andersen.banking.deposit_impl.mapping.TransferKafkaMessageMapper;
import com.andersen.banking.deposit_impl.service.DepositService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class TransferMoneyServiceKafkaListener {

    private KafkaConfigProperties kafkaProperties;
    private DepositService depositService;

    private TransferKafkaMessageMapper messageMapper;

    @KafkaListener(topics = "${spring.kafka.topicName}", groupId = "${spring.kafka.groupId}")
    public void listenTransferMoneyService(@Payload List<TransferKafkaMessageDto> messages,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(message -> depositService.makeTransfer(messageMapper.toTransferKafkaMessage(message)));
    }
}
