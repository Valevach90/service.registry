package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import org.apache.commons.lang3.tuple.ImmutablePair;

public interface TransferMoneyService {

    /**
     * @param requestTransferMessage
     * @return
     */
    ResponseTransferMessage executeTransfer(
            RequestTransferMessage requestTransferMessage);
}
