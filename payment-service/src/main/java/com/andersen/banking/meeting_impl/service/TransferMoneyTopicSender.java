package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.ResponseKafkaTransferMessage;

public interface TransferMoneyTopicSender {

    /**
     * @param responseKafkaTransferMessage
     */
    void sendResponseMessage(ResponseKafkaTransferMessage responseKafkaTransferMessage);
}
