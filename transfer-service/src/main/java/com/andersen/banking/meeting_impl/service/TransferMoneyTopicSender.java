package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;

public interface TransferMoneyTopicSender {

    /**
     * @param topicName
     * @param requestKafkaTransferMessage
     */
    void sendRequestMessage(String topicName, RequestKafkaTransferMessage requestKafkaTransferMessage);

}
