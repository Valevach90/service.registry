package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.ResponseKafkaTransferMessage;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface TransferMoneyTopicListener {

    /**
     * @param messages
     */
    void listenResponseMessage(@Payload List<ResponseKafkaTransferMessage> messages);

}
