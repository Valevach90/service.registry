package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.entities.LinkedCard;
import com.andersen.banking.meeting_db.entities.StatusDescription;
import com.andersen.banking.meeting_db.entities.Transfer;
import com.andersen.banking.meeting_db.repositories.DepositRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage.ResponseTransferMessageBuilder;
import com.andersen.banking.meeting_impl.mapping.TransferMapper;
import com.andersen.banking.meeting_impl.service.DepositService;
import com.andersen.banking.meeting_impl.service.TransferService;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private static final String TRANSFER_WITH_DEPOSIT_TYPE = "Deposit";
    private static final Integer LENGTH_OF_DEPOSIT_NUMBER = 16;

    private static final Long MILLIS_IN_MONTH = 2592000000L;

    private final DepositRepository depositRepository;

    private final TransferService transferService;

    private final TransferMapper transferMapper;

    @Override
    @Retryable(value = SQLIntegrityConstraintViolationException.class, backoff = @Backoff(delay = 1000))
    @Transactional
    public Deposit create(Deposit deposit) {
        log.info("Creating deposit: {}", deposit);

        deposit.setId(null);
        deposit.setDepositNumber(String.format("%0" + LENGTH_OF_DEPOSIT_NUMBER + "d", (depositRepository.count() + 1)));
        deposit.setOpenDate(new java.sql.Date(System.currentTimeMillis()));
        deposit.setCloseDate(new java.sql.Date(System.currentTimeMillis() + MILLIS_IN_MONTH * deposit.getTermMonths()));
        deposit.setIsActive(true);

        for (LinkedCard cards : deposit.getLinkedCards()){
            cards.setDeposit(deposit);
        }

        Deposit savedDeposit = depositRepository.save(deposit);

        log.info("Created deposit: {}", savedDeposit);
        return savedDeposit;

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Deposit> findById(UUID id) {
        log.info("Find deposit by id: {}", id);

        Optional<Deposit> deposit = depositRepository.findById(id);

        log.info("Found deposit by id: {}", deposit);
        return deposit;
    }

    @Override
    public Page<Deposit> findDepositByUserId(UUID userId, Pageable pageable) {
        log.info("Find all deposits for user {} and pageable: {}", userId, pageable);

        Page<Deposit> pageOfDeposits = depositRepository.findDepositByUserId(userId, pageable);

        log.info("Found {} deposits", pageOfDeposits.getContent().size());
        return pageOfDeposits;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Deposit> findAll(Pageable pageable) {
        log.info("Find all deposits for pageable: {}", pageable);

        Page<Deposit> pageOfDeposits = depositRepository.findAll(pageable);

        log.info("Found {} deposits", pageOfDeposits.getContent().size());
        return pageOfDeposits;
    }

    @Override
    @Transactional
    public void update(Deposit deposit) {
        log.info("Updating deposit: {}", deposit);

        Deposit foundDeposit = depositRepository.findById(deposit.getId())
                .orElseThrow(() -> new NotFoundException(Deposit.class, deposit.getId()));

        depositRepository.save(deposit);

        log.info("Deposit: {} updated to version: {}", foundDeposit, deposit);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        log.info("Deleting deposit with id: {}", id);

        Deposit foundDeposit = depositRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Deposit.class, id));

        depositRepository.deleteById(id);

        log.info("Deleted deposit: {}", foundDeposit);
    }

    @Override
    @Transactional(rollbackFor = NotFoundException.class)
    public ResponseTransferMessage makeTransfer(RequestTransferMessage message) {
        log.info("Trying make transfer using message: {}", message);
        ResponseTransferMessage resp;
        if (!transferService.isExist(message.getTransferId())) {

            Transfer transfer = transferMapper.toTransfer(message);

            try {
                if (message.getSourceType().equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)
                        && message.getDestinationType()
                        .equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)) {
                    transfer.setStatus(setStatusInt(transferBetweenDeposits(message)));
                    transfer.setStatusDescription(StatusDescription.DEPOSIT.getDescription());

                } else if (message.getDestinationType()
                        .equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)) {
                    transfer.setStatus(setStatusInt(replenishDeposit(message)));
                    transfer.setStatusDescription(StatusDescription.REPLENISHMENT.getDescription());

                } else if (message.getSourceType().equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)) {
                    transfer.setStatus(setStatusInt(withdrawalDeposit(message)));
                    transfer.setStatusDescription(StatusDescription.WITHDRAWAL.getDescription());
                }
            } catch (NotFoundException exception) {
                transfer.setStatus(setStatusInt(false));
                transfer.setStatusDescription(StatusDescription.FAILED.getDescription());
            }

            log.info("Saving transfer: {}", transfer);

            transferService.create(transfer);

            log.info("Sending response message: {}", transfer);
            resp = createResponse(transfer);

        } else {

            log.info("Transfer with id equal to transfer id from request message {} already exists",
                    message);
            ResponseTransferMessageBuilder builder = ResponseTransferMessage.builder();

            Integer status = message.getStatus();
            if (status == Status.STATUS_ROLLING_BACK) {
                builder.status(rollingBackTransfer(message));
                builder.statusDescription(StatusDescription.ROLLED_BACK.getDescription());
            } else if (status == Status.STATUS_ACTIVE) {
                builder.status(Status.STATUS_UNKNOWN);
                builder.statusDescription(StatusDescription.EXIST.getDescription());
            }
            resp = builder.build();
        }
        return resp;
    }

    private int rollingBackTransfer(RequestTransferMessage message) {
        Long amount = message.getAmount();
        int status = Status.STATUS_UNKNOWN;

        message.setAmount(amount * (-1));
        if (message.getDestinationType()
                .equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)) {
            status = setStatusInt(replenishDeposit(message));

        } else if (message.getSourceType().equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)) {
            status = setStatusInt(withdrawalDeposit(message));
        }
        return status == Status.STATUS_COMMITTED ? Status.STATUS_ROLLEDBACK
                : Status.STATUS_NO_TRANSACTION;
    }

    @Transactional
    public Boolean transferBetweenDeposits(RequestTransferMessage message) {
        log.info("Trying make transfer between deposits, transfer request message: {}", message);

        Deposit destinationDeposit = depositRepository.findByDepositNumber(
                        message.getDestinationNumber())
                .orElseThrow(
                        () -> new NotFoundException(Deposit.class, message.getDestinationNumber()));
        Deposit sourceDeposit = depositRepository.findByDepositNumber(message.getSourceNumber())
                .orElseThrow(() -> new NotFoundException(Deposit.class, message.getSourceNumber()));

        if (sourceDeposit.getAmount() >= message.getAmount()) {

            sourceDeposit.setAmount(sourceDeposit.getAmount() - message.getAmount());
            destinationDeposit.setAmount(destinationDeposit.getAmount() + message.getAmount());

            log.info("Saving deposit after transfer {}", destinationDeposit);
            depositRepository.save(destinationDeposit);
            log.info("Saving deposit after transfer {}", sourceDeposit);
            depositRepository.save(sourceDeposit);

            log.info("Withdrawal successful for transfer request message: {}", message);
            return true;
        } else {
            log.error(
                    "Withdrawal failed (source deposit has not enough money), transfer request message: {}",
                    message);
            return false;
        }
    }

    @Transactional
    public Boolean replenishDeposit(RequestTransferMessage message) {
        log.info("Trying replenish deposit using message: {}", message);

        Deposit destinationDeposit = depositRepository.findByDepositNumber(
                        message.getDestinationNumber())
                .orElseThrow(
                        () -> new NotFoundException(Deposit.class, message.getDestinationNumber()));

        destinationDeposit.setAmount(destinationDeposit.getAmount() + message.getAmount());

        log.info("Saving deposit after transfer {}", destinationDeposit);
        depositRepository.save(destinationDeposit);

        log.info("Replenishment successful for message: {}", message);
        return true;
    }

    @Transactional
    public Boolean withdrawalDeposit(RequestTransferMessage message) {
        log.info("Trying withdrawal deposit using message: {}", message);

        Deposit sourceDeposit = depositRepository.findByDepositNumber(message.getSourceNumber())
                .orElseThrow(() -> new NotFoundException(Deposit.class, message.getSourceNumber()));

        if (sourceDeposit.getAmount() >= message.getAmount()) {

            sourceDeposit.setAmount(sourceDeposit.getAmount() - message.getAmount());

            log.info("Saving deposit after transfer {}", sourceDeposit);
            depositRepository.save(sourceDeposit);

            log.info("Withdrawal successful for message: {}", message);
            return true;
        } else {
            log.error(
                    "Withdrawal failed (source deposit has not enough money), transfer request message: {}",
                    message);
            return false;
        }
    }

    private ResponseTransferMessage createResponse(Transfer transfer) {
        log.info("Creating response message based on result of transfer: {}", transfer);

        ResponseTransferMessageBuilder builder = ResponseTransferMessage.builder();
        if (transfer.getSourceType().equalsIgnoreCase(transfer.getDestinationType())) {
            builder.status(transfer.getStatus());
        } else {
            builder.service("deposit");
            if (transfer.getStatus() == Status.STATUS_COMMITTED) {
                builder.status(Status.STATUS_COMMITTING);
            } else {
                builder.status(Status.STATUS_ROLLING_BACK);
            }
        }

        ResponseTransferMessage response = builder
                .transferId(transfer.getTransferId())
                .statusDescription(transfer.getStatusDescription())
                .build();

        log.info("Sending response message with transfer result to Transfer service: {}", response);

        return response;
    }

    private int setStatusInt(boolean value) {
        return value ? Status.STATUS_COMMITTED : Status.STATUS_ROLLEDBACK;
    }

    @Transactional
    public Deposit accrualDepositInterest(UUID depositId, Long startedPeriodAmount) {

        log.info("Trying accrual of interest for deposit with id: {}", depositId);

        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new NotFoundException(Deposit.class, depositId));

        if (deposit.getAmount() >= startedPeriodAmount) {
            deposit.setAmount(deposit.getAmount() + (deposit.getAmount() * deposit.getInterestRate() / 100));

            log.info("Saving deposit after successful interest accrual {}", deposit);
            depositRepository.save(deposit);
        } else {
            log.error("Interest accrual forbidden, amount is less than stated period amount {}", deposit);
        }
        return deposit;
    }
}
