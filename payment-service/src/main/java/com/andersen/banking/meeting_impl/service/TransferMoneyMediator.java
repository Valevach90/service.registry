package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;

public interface TransferMoneyMediator {

    /**
     * @param requestTransferMessage
     */
    ResponseTransferMessage run(RequestTransferMessage requestTransferMessage);
}
