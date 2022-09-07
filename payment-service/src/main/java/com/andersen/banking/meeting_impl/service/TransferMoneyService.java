package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import org.apache.commons.lang3.tuple.ImmutablePair;

public interface TransferMoneyService {

    /**
     * @param requestKafkaTransferMessage
     * @param source
     * @param target
     */
    void doFilterBeforeTransfer(
            RequestKafkaTransferMessage requestKafkaTransferMessage,
            Account source,
            Account target);

    /**
     * @param requestKafkaTransferMessage
     * @return
     */
    ImmutablePair<Boolean, StringBuffer> executeTransfer(
            RequestKafkaTransferMessage requestKafkaTransferMessage);
}
