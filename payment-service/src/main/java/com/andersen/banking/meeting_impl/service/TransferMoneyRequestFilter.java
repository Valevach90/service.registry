package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;

public interface TransferMoneyRequestFilter {

    /**
     * @param requestTransferMessage
     * @param account
     */
    void checkOnEqualsCurrency(
            RequestTransferMessage requestTransferMessage,
            Account account);

    /**
     * @param requestTransferMessage
     * @param srcAccount
     * @param destAccount
     */
    void checkOnEqualsCurrency(
            RequestTransferMessage requestTransferMessage,
            Account srcAccount,
            Account destAccount);

    /**
     * @param requestTransferMessage
     * @param srcAccount
     * @param destAccount
     */
    void checkOnEqualsAccountsNumber(
            RequestTransferMessage requestTransferMessage,
            Account srcAccount,
            Account destAccount);

    /**
     * @param requestTransferMessage
     * @param srcAccount
     */
    void checkOnEqualsUserIdAndAccountOwnerId(
            RequestTransferMessage requestTransferMessage, Account srcAccount);

    /**
     * @param requestTransferMessage
     * @param account
     */
    void checkOnEnoughMoneyOnSrcAccount(
            RequestTransferMessage requestTransferMessage, Account account);
}
