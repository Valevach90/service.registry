package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;

public interface TransferMoneyTopicListener {

    ResponseTransferMessage execute(RequestTransferMessage message);
}
