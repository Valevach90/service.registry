package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;

@FunctionalInterface
public interface TransferMoneyMediator {

    /**
     * @param requestKafkaTransferMessage
     */
    void run(RequestKafkaTransferMessage requestKafkaTransferMessage);
}
