package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.exception.AccountCurrencyException;
import com.andersen.banking.meeting_impl.exception.DifferentTransferTypeException;
import com.andersen.banking.meeting_impl.exception.NotEnoughMoneyOnAccountException;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferMoneyRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyRequestFilterImpl implements TransferMoneyRequestFilter {

    private static final String CARD = "CARD";

    /*
       It could rewrite to 'Chain of responsibility' pattern in the future.
    */

    public void checkOnEqualsPaymentTypes(RequestKafkaTransferMessage requestKafkaTransferMessage) {
        if (!requestKafkaTransferMessage
                        .getSourceType()
                        .equals(requestKafkaTransferMessage.getDestinationType())
                || !CARD.equalsIgnoreCase(requestKafkaTransferMessage.getSourceType())) {

            log.debug(
                    "Received request from transfer-service with id = {} have different src and desc types to transfer card to card.",
                    requestKafkaTransferMessage.getTransferId());
            throw new DifferentTransferTypeException(requestKafkaTransferMessage.getTransferId());
        }
        log.info(
                "Checked on equals types for transfer with id : {}",
                requestKafkaTransferMessage.getTransferId());
    }

    public void checkOnEqualsCurrency(
            RequestKafkaTransferMessage requestKafkaTransferMessage,
            Account srcAccount,
            Account destAccount) {
        if (!requestKafkaTransferMessage.getCurrencyName().equals(srcAccount.getCurrency())
                || !requestKafkaTransferMessage.getCurrencyName().equals(destAccount.getCurrency())
                || !srcAccount.getCurrency().equals(destAccount.getCurrency())) {

            log.debug(
                    "Src and Dest card is different by currency accounts. Transfer id : {}",
                    requestKafkaTransferMessage.getTransferId());
            throw new AccountCurrencyException(requestKafkaTransferMessage.getTransferId());
        }

        log.info(
                "Checked on equals currency for transfer with id : {}",
                requestKafkaTransferMessage.getTransferId());
    }

    public void checkOnEqualsAccountsNumber(
            RequestKafkaTransferMessage requestKafkaTransferMessage,
            Account srcAccount,
            Account destAccount) {
        if (srcAccount.getAccountNumber().equals(destAccount.getAccountNumber())) {

            log.info(
                    "Src and Dest card have seem account for transfer with id : {}",
                    requestKafkaTransferMessage.getTransferId());
            throw new RuntimeException("Src and Dest card have seem account");
        }

        log.info(
                "Checked on equals account numbers for transfer with id : {}",
                requestKafkaTransferMessage.getTransferId());
    }

    public void checkOnEqualsUserIdAndAccountOwnerId(
            RequestKafkaTransferMessage requestKafkaTransferMessage, Account srcAccount) {
        if (!srcAccount.getOwnerId().equals(requestKafkaTransferMessage.getUserId())) {

            log.info(
                    "Can't get money from not owned account, transfer id : {}",
                    requestKafkaTransferMessage.getTransferId());
            throw new RuntimeException(
                    "Can't access to get money for user with id "
                            + requestKafkaTransferMessage.getUserId());
        }

        log.info(
                "Checked on equals user id and SRC account's owner id for transfer with id : {}",
                requestKafkaTransferMessage.getTransferId());
    }

    public void checkOnEnoughMoneyOnSrcAccount(
            RequestKafkaTransferMessage requestKafkaTransferMessage, Account account) {
        long srcNewBalance = account.getBalance() - requestKafkaTransferMessage.getAmount();
        if (srcNewBalance < 0) {

            log.info(
                    "account with id : {} have not enough money, transfer id : {}",
                    account.getId(),
                    requestKafkaTransferMessage.getTransferId());
            throw new NotEnoughMoneyOnAccountException(account.getId());
        }
    }
}
