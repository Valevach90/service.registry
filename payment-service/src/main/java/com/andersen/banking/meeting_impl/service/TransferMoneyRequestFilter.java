package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;

public interface TransferMoneyRequestFilter {

    /**
     * @param requestKafkaTransferMessage
     */
    void checkOnEqualsPaymentTypes(RequestKafkaTransferMessage requestKafkaTransferMessage);

    /**
     * @param requestKafkaTransferMessage
     * @param srcAccount
     * @param destAccount
     */
    void checkOnEqualsCurrency(
            RequestKafkaTransferMessage requestKafkaTransferMessage,
            Account srcAccount,
            Account destAccount);

    /**
     * @param requestKafkaTransferMessage
     * @param srcAccount
     * @param destAccount
     */
    void checkOnEqualsAccountsNumber(
            RequestKafkaTransferMessage requestKafkaTransferMessage,
            Account srcAccount,
            Account destAccount);

    /**
     * @param requestKafkaTransferMessage
     * @param srcAccount
     */
    void checkOnEqualsUserIdAndAccountOwnerId(
            RequestKafkaTransferMessage requestKafkaTransferMessage, Account srcAccount);

    /**
     * @param requestKafkaTransferMessage
     * @param account
     */
    void checkOnEnoughMoneyOnSrcAccount(
            RequestKafkaTransferMessage requestKafkaTransferMessage, Account account);
}
