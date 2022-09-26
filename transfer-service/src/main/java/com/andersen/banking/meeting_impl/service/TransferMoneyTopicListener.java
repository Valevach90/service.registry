package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;

public interface TransferMoneyTopicListener {
    void listenMessage(ResponseTransferMessage message);
}
