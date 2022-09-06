package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface TransferMoneyTopicListener {

    /**
     * @param messages
     */
    void listenKafkaTopicOnInternalTransfer(@Payload List<RequestKafkaTransferMessage> messages);
}
