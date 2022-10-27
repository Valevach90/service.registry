package com.andersen.banking.meeting_impl.service.impl.replenishment;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.StatusDescription;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_impl.exception.CardIsNotReadyToUseException;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage.ResponseTransferMessageBuilder;
import com.andersen.banking.meeting_impl.service.AccountService;
import com.andersen.banking.meeting_impl.service.TransferMoneyRequestFilter;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransferReplenishmentMoneyServiceImpl implements TransferMoneyService {

    private static final int LAST_FOUR_NUMBERS_CARD = 4;
    private final TransferMoneyRequestFilter transferMoneyRequestFilter;
    private final CardRepository cardRepository;
    private final AccountService accountService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class)
    public ResponseTransferMessage executeTransfer(
            RequestTransferMessage requestTransferMessage) {

        final UUID transferId = requestTransferMessage.getTransferId();

        ResponseTransferMessageBuilder builder = ResponseTransferMessage.builder()
                .transferId(transferId);

        Account targetAccount = getTargetAccount(requestTransferMessage);

        if(requestTransferMessage.getSourceType() == "CARD") {
            doFilterBeforeTransfer(requestTransferMessage, targetAccount);
        }

        if(requestTransferMessage.getSourceType().equals("DEPOSIT")) {
            requestTransferMessage.setAmount(requestTransferMessage.getAmount() * -1L);
        }

        boolean isTransferred = accountService.changeAccountBalance(targetAccount.getId(),
                requestTransferMessage.getAmount());
        if (!isTransferred){
            log.info("Transfer operation failed. Transfer id : {}.", transferId);

            builder.statusDescription(StatusDescription.FAILED.getDescription());
            builder.status(Status.STATUS_ROLLING_BACK);
        } else if (requestTransferMessage.getSourceType().equals("CARD")){
            log.info("Successfully transfer money. Transfer id : {}", transferId);

            builder.statusDescription(StatusDescription.GET_FROM_CARD.getDescription());
            builder.status(Status.STATUS_COMMITTING);
        } else if (requestTransferMessage.getSourceType().equals("DEPOSIT")){
            log.info("Successfully transfer money from deposit : {}. Transfer id : {}",
                    requestTransferMessage.getSourceNumber(), transferId);

            builder.statusDescription(StatusDescription.GET_FROM_DEPOSIT.getDescription());
            builder.status(Status.STATUS_COMMITTING);
        }

        builder.service("payment");
        return builder.build();
    }

    private Account getTargetAccount(RequestTransferMessage requestTransferMessage) {

        String requestDestNum = requestTransferMessage.getDestinationNumber();
        log.info("Getting cards by request destination nums = {}", requestDestNum);
        int srcHash = requestDestNum.length() - LAST_FOUR_NUMBERS_CARD;

        Optional<Card> optionalCardSrc = cardRepository
                .findByFirstTwelveNumbersAndLastFourNumbers(
                        requestDestNum.substring(0, srcHash), requestDestNum.substring(srcHash));

        Card card = optionalCardSrc.orElseThrow(()->new NotFoundException(Card.class));
        if(!card.isActive()){
            throw new CardIsNotReadyToUseException(requestTransferMessage.getUserId());
        }

        return optionalCardSrc.orElseThrow(() -> new NotFoundException(Card.class)).getAccount();
    }

    private void doFilterBeforeTransfer(RequestTransferMessage requestTransferMessage,
            Account source) throws RuntimeException {

        if(requestTransferMessage.getSourceType().equals("CARD")) {
            /*
            check on ownerId before the transfer will begin
         */
            transferMoneyRequestFilter.checkOnEnoughMoneyOnSrcAccount(
                    requestTransferMessage, source);
        }

        /*
            check on equals account's owner id and customer's id
         */
        transferMoneyRequestFilter.checkOnEqualsUserIdAndAccountOwnerId(
                requestTransferMessage, source);
        /*
            check on the valid currency
         */
        transferMoneyRequestFilter.checkOnEqualsCurrency(
                requestTransferMessage, source);
    }

}

