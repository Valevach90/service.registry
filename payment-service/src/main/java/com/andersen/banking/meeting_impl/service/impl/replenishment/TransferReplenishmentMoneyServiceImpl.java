package com.andersen.banking.meeting_impl.service.impl.replenishment;

import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.StatusDescription;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_impl.exception.NotEnoughMoneyOnAccountException;
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

        try {

            Account sourceAccount = getSourceAccount(requestTransferMessage);

            doFilterBeforeTransfer(requestTransferMessage, sourceAccount);

            debit(sourceAccount, requestTransferMessage.getAmount());
            log.info("Successfully transfer money. Transfer id : {}", transferId);
            builder.statusDescription(StatusDescription.GET_FROM_CARD.getDescription());

            builder.status(Status.STATUS_COMMITTING);
        } catch (Exception e) {
            log.info("Transfer operation failed. Transfer id : {}. Reason : {}", transferId,
                    e.getMessage());
            builder.statusDescription(StatusDescription.FAILED.getDescription());
            builder.status(TransferUtil.setTransactionalStatus(false));
        }

        builder.service("payment");
        return builder.build();
    }

    private Account getSourceAccount(RequestTransferMessage requestTransferMessage) {

        String requestDestNum = requestTransferMessage.getSourceNumber();
        log.info("Getting cards by request destination nums = {}", requestDestNum);
        int srcHash = requestDestNum.length() - 4;

        Optional<Card> optionalCardSrc = cardRepository
                .findByFirstTwelveNumbersAndLastFourNumbers(
                        requestDestNum.substring(0, srcHash), requestDestNum.substring(srcHash));

        return optionalCardSrc.orElseThrow(() -> new NotFoundException(Card.class)).getAccount();
    }

    private void doFilterBeforeTransfer(RequestTransferMessage requestTransferMessage,
            Account source) throws RuntimeException {

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
        /*
            check on ownerId before the transfer will begin
         */
        transferMoneyRequestFilter.checkOnEnoughMoneyOnSrcAccount(
                requestTransferMessage, source);
    }

    private void debit(Account sourceAccount, long amount) {
        long balance = sourceAccount.getBalance();
        long savingBalance = balance - amount;
        if (savingBalance < 0) {
            throw new NotEnoughMoneyOnAccountException(sourceAccount.getId());
        }
        sourceAccount.setBalance(savingBalance);
        accountService.update(sourceAccount);
    }
}

