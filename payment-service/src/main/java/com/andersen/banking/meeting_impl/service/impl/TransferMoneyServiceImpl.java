package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.service.TransferMoneyRequestFilter;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import com.andersen.banking.meeting_impl.util.CryptWithSHA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyServiceImpl implements TransferMoneyService {

    private final static int CARD_NUMBER_HASH_LENGTH = 64 + 4;

    private final TransferMoneyRequestFilter transferMoneyRequestFilter;

    private final CardRepository cardRepository;

    private final AccountService accountService;

    /*
        stages:
        - get source and target accounts
        - filter values in the request message in relation of accounts
        - if not exception => transfer money
        - return pair of the result and comment of the result.
     */

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public ImmutablePair<Boolean, StringBuffer> executeTransfer(RequestKafkaTransferMessage requestKafkaTransferMessage) {

        final UUID transferId = requestKafkaTransferMessage.getTransferId();

        ImmutablePair<Account, Account> accounts;
        Account sourceAccount;
        Account targetAccount;

        boolean transferResult = false;
        StringBuffer transferMessage = new StringBuffer();

        try {

            accounts = getSourceAndTargetAccountUsingRequestKafkaTransferMessage(requestKafkaTransferMessage);

            sourceAccount = accounts.left;
            targetAccount = accounts.right;

            doFilterBeforeTransfer(requestKafkaTransferMessage, sourceAccount, targetAccount);

        } catch (Exception e) {
            log.info("Transfer operation failed. Transfer id : {}. Reason : {}", transferId, e.getMessage());
            transferMessage.append("Transfer operation failed. Reason : ").append(e.getMessage());

            return new ImmutablePair<>(transferResult, transferMessage);
        }

        transferResult = accountService.transfer(sourceAccount, targetAccount, requestKafkaTransferMessage.getAmount());
        if (transferResult) {
            log.info("Successfully transfer money. Transfer id : {}", transferId);
            transferMessage.append("Successfully transfer money");
        }

        return new ImmutablePair<>(transferResult, transferMessage);
    }


    /*
        Method return pair of source and target accounts by card information in the request message (card numbers).
     */

    @Transactional(readOnly = true)
    ImmutablePair<Account, Account> getSourceAndTargetAccountUsingRequestKafkaTransferMessage(RequestKafkaTransferMessage requestKafkaTransferMessage) {

        String requestSrcCardNumber = requestKafkaTransferMessage.getSourceNumber();
        String requestDestCardNumber = requestKafkaTransferMessage.getDestinationNumber();

        ImmutablePair<Card, Card> cards = getSrcAndDestinationCard(requestSrcCardNumber, requestDestCardNumber);

        Card srcCard = cards.left;
        Card destCard = cards.right;

        Account srcAccount = srcCard.getAccount();
        Account destAccount = destCard.getAccount();

        return new ImmutablePair<>(srcAccount, destAccount);
    }


    @Override
    public void doFilterBeforeTransfer(RequestKafkaTransferMessage requestKafkaTransferMessage, Account source, Account target) throws RuntimeException {

            /*
                check on equals account's owner id and customer's id
             */

        transferMoneyRequestFilter.checkOnEqualsUserIdAndAccountOwnerId(requestKafkaTransferMessage, source);

            /*
                check on src and desc type is equals card.
            */

        transferMoneyRequestFilter.checkOnEqualsPaymentTypes(requestKafkaTransferMessage);

            /*
                check on the valid currency
             */

        transferMoneyRequestFilter.checkOnEqualsCurrency(requestKafkaTransferMessage, source, target);

            /*
                check on the same account for cards
             */

        transferMoneyRequestFilter.checkOnEqualsAccountsNumber(requestKafkaTransferMessage, source, target);

            /*
                check on ownerId before the transfer will begin
             */

        transferMoneyRequestFilter.checkOnEnoughMoneyOnSrcAccount(requestKafkaTransferMessage, source);

    }

    private ImmutablePair<Card, Card> getSrcAndDestinationCard(String requestSrcNum, String requestDestNum) {
        log.info("Getting cards by request nums: src = {} and dest = {}", requestSrcNum, requestDestNum );

        Optional<Card> optionalCardSrc = cardRepository
                .findByFirstTwelveNumbersAndLastFourNumbers(requestSrcNum.substring(0, 64), requestSrcNum.substring(64, 68));

        Optional<Card> optionalCardDest = cardRepository
                .findByFirstTwelveNumbersAndLastFourNumbers(requestDestNum.substring(0, 64), requestDestNum.substring(64, 68));


        if (optionalCardSrc.isEmpty()) throw new NotFoundException(Card.class);
        if (optionalCardDest.isEmpty()) throw new NotFoundException(Card.class);

        return new ImmutablePair<>(optionalCardSrc.get(), optionalCardDest.get());
    }
}

