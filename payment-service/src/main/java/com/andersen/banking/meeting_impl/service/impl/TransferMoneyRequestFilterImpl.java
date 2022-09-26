package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_impl.exception.AccountCurrencyException;
import com.andersen.banking.meeting_impl.exception.NotEnoughMoneyOnAccountException;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferMoneyRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyRequestFilterImpl implements TransferMoneyRequestFilter {

    public void checkOnEqualsCurrency(
            RequestTransferMessage requestTransferMessage,
            Account source) {
        checkOnEqualsCurrency(requestTransferMessage, source, null);
    }


    public void checkOnEqualsCurrency(
            RequestTransferMessage requestTransferMessage,
            Account srcAccount,
            Account destAccount) {
        if (!requestTransferMessage.getCurrencyName().equals(srcAccount.getCurrency())
                || destAccount != null
                && (!requestTransferMessage.getCurrencyName().equals(destAccount.getCurrency())
                || !srcAccount.getCurrency().equals(destAccount.getCurrency()))) {

            log.debug(
                    "Src or Dest card is different by currency accounts. Transfer id : {}",
                    requestTransferMessage.getTransferId());
            throw new AccountCurrencyException(requestTransferMessage.getTransferId());
        }

        log.info(
                "Checked on equals currency for transfer with id : {}",
                requestTransferMessage.getTransferId());
    }

    public void checkOnEqualsAccountsNumber(
            RequestTransferMessage requestTransferMessage,
            Account srcAccount,
            Account destAccount) {
        if (srcAccount.getAccountNumber().equals(destAccount.getAccountNumber())) {

            log.info(
                    "Src and Dest card have seem account for transfer with id : {}",
                    requestTransferMessage.getTransferId());
            throw new RuntimeException("Src and Dest card have seem account");
        }

        log.info(
                "Checked on equals account numbers for transfer with id : {}",
                requestTransferMessage.getTransferId());
    }

    public void checkOnEqualsUserIdAndAccountOwnerId(
            RequestTransferMessage requestTransferMessage, Account srcAccount) {
        if (!srcAccount.getOwnerId().equals(requestTransferMessage.getUserId())) {

            log.info(
                    "Can't get money from not owned account, transfer id : {}",
                    requestTransferMessage.getTransferId());
            throw new RuntimeException(
                    "Can't access to get money for user with id "
                            + requestTransferMessage.getUserId());
        }

        log.info(
                "Checked on equals user id and SRC account's owner id for transfer with id : {}",
                requestTransferMessage.getTransferId());
    }

    public void checkOnEnoughMoneyOnSrcAccount(
            RequestTransferMessage requestTransferMessage, Account account) {
        long srcNewBalance = account.getBalance() - requestTransferMessage.getAmount();
        if (srcNewBalance < 0) {

            log.info(
                    "account with id : {} have not enough money, transfer id : {}",
                    account.getId(),
                    requestTransferMessage.getTransferId());
            throw new NotEnoughMoneyOnAccountException(account.getId());
        }
    }
}
