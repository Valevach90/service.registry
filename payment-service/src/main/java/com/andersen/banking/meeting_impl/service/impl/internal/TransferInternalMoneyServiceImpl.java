package com.andersen.banking.meeting_impl.service.impl.internal;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.StatusDescription;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage.ResponseTransferMessageBuilder;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.service.TransferMoneyRequestFilter;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import com.andersen.banking.meeting_impl.util.TransferUtil;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransferInternalMoneyServiceImpl implements TransferMoneyService {

    private final TransferMoneyRequestFilter transferMoneyRequestFilter;

    private final CardRepository cardRepository;

    private final AccountService accountService;

    private static final int LAST_FOUR_NUMBERS_CARD = 4;

    /*
        stages:
        - get source and target accounts
        - filter values in the request message in relation of accounts
        - if not exception => transfer money
        - return pair of the result and comment of the result.
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public ResponseTransferMessage executeTransfer(
            RequestTransferMessage message) {
        final UUID transferId = message.getTransferId();

        ResponseTransferMessageBuilder builder = ResponseTransferMessage.builder()
                .transferId(transferId);

        try {

            ImmutablePair<Account, Account> accounts = getSourceAndTargetAccountUsingRequestKafkaTransferMessage(
                    message);

            Account sourceAccount = accounts.left;
            Account targetAccount = accounts.right;

            doFilterBeforeTransfer(message, sourceAccount, targetAccount);
            boolean transferResult = accountService.transfer(sourceAccount, targetAccount,
                    message.getAmount());
            if (transferResult) {
                log.info("Successfully transfer money. Transfer id : {}", transferId);
                builder.statusDescription(StatusDescription.BETWEEN_CARDS.getDescription());
            }
            builder.status(TransferUtil.setTransactionalStatus(transferResult));
        } catch (Exception e) {
            log.info("Transfer operation failed. Transfer id : {}. Reason : {}", transferId,
                    e.getMessage());
            builder.status(TransferUtil.setTransactionalStatus(false));
            builder.statusDescription(StatusDescription.FAILED.getDescription());
        }
        return builder.build();
    }


    /*
        Method return pair of source and target accounts by card information in the request message (card numbers).
     */

    ImmutablePair<Account, Account> getSourceAndTargetAccountUsingRequestKafkaTransferMessage(
            RequestTransferMessage requestTransferMessage) {

        String requestSrcCardNumber = requestTransferMessage.getSourceNumber();
        String requestDestCardNumber = requestTransferMessage.getDestinationNumber();

        ImmutablePair<Card, Card> cards = getSrcAndDestinationCard(requestSrcCardNumber,
                requestDestCardNumber);

        Card srcCard = cards.left;
        Card destCard = cards.right;

        Account srcAccount = srcCard.getAccount();
        Account destAccount = destCard.getAccount();

        return new ImmutablePair<>(srcAccount, destAccount);
    }

    private void doFilterBeforeTransfer(RequestTransferMessage requestTransferMessage,
            Account source, Account target) throws RuntimeException {

        /*
            check on equals account's owner id and customer's id
        */
        transferMoneyRequestFilter.checkOnEqualsUserIdAndAccountOwnerId(
                requestTransferMessage, source);
        /*
            check on the valid currency
        */
        transferMoneyRequestFilter.checkOnEqualsCurrency(
                requestTransferMessage, source, target);
        /*
            check on the same account for cards
        */
        transferMoneyRequestFilter.checkOnEqualsAccountsNumber(
                requestTransferMessage, source, target);
        /*
            check on ownerId before the transfer will begin
        */
        transferMoneyRequestFilter.checkOnEnoughMoneyOnSrcAccount(
                requestTransferMessage, source);
    }

    private ImmutablePair<Card, Card> getSrcAndDestinationCard(String requestSrcNum,
            String requestDestNum) {
        log.info("Getting cards by request nums: src = {} and dest = {}", requestSrcNum,
                requestDestNum);
        int srcHash = requestSrcNum.length() - LAST_FOUR_NUMBERS_CARD;
        int destHash = requestDestNum.length() - LAST_FOUR_NUMBERS_CARD;

        Optional<Card> optionalCardSrc = cardRepository
                .findByFirstTwelveNumbersAndLastFourNumbers(
                        requestSrcNum.substring(0, srcHash), requestSrcNum.substring(srcHash));

        Optional<Card> optionalCardDest = cardRepository
                .findByFirstTwelveNumbersAndLastFourNumbers(
                        requestDestNum.substring(0, destHash), requestDestNum.substring(destHash));

        return new ImmutablePair<>(
                optionalCardSrc.orElseThrow(() -> new NotFoundException(Card.class)),
                optionalCardDest.orElseThrow(() -> new NotFoundException(Card.class)));
    }
}

