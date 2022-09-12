package com.andersen.banking.deposit_impl.service.impl;

import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseKafkaTransferMessage;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_db.entities.Transfer;
import com.andersen.banking.deposit_db.repositories.DepositRepository;
import com.andersen.banking.deposit_db.repositories.TransferRepository;
import com.andersen.banking.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit_impl.kafka.TransferMoneyServiceKafkaResponseProducer;
import com.andersen.banking.deposit_impl.mapping.TransferMapper;
import com.andersen.banking.deposit_impl.service.DepositService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private static final String TRANSFER_WITH_DEPOSIT_TYPE = "Deposit";

    private final DepositRepository depositRepository;

    private final TransferRepository transferRepository;

    private final TransferMapper transferMapper;

    private final TransferMoneyServiceKafkaResponseProducer transferMoneyServiceKafkaResponseProducer;

    @Override
    @Transactional
    public Deposit create(Deposit deposit) {
        log.info("Creating deposit: {}", deposit);

        deposit.setId(null);

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
        log.info("Find all deposits for user {} and pageable: {}",userId , pageable);

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
    @Transactional (rollbackFor = NotFoundException.class)
    public void makeTransfer(RequestKafkaTransferMessage message) {
        log.info("Trying make transfer using message: {}", message);

        if (Objects.nonNull(message) && isFirstTransferAttempt(message.getTransferId())) {

            Transfer transfer = transferMapper.toTransfer(message);

            try {
                if (message.getSourceType().equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE) && message.getDestinationType().equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)) {
                    transfer.setResult(transferBetweenDeposits(message));

                } else if (message.getDestinationType().equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)) {
                    transfer.setResult(replenishDeposit(message));

                } else if (message.getSourceType().equalsIgnoreCase(TRANSFER_WITH_DEPOSIT_TYPE)) {
                    transfer.setResult(withdrawalDeposit(message));
                }
            } catch (NotFoundException exception){
                transfer.setResult(false);
                transfer.setStatusDescription(exception.getMessage());
            }

            log.info("Saving transfer: {}", transfer);
            //transferRepository.save(transfer);

            log.info("Sending response message: {}", transfer);
            createAndSendResponse(transfer);

        } else {
            log.info("Transfer with id equal to transfer id from request message {} already exists", message);
        }
    }

    @Transactional
    public Boolean transferBetweenDeposits(RequestKafkaTransferMessage message) {
        log.info("Trying make transfer between deposits, transfer request message: {}", message);

        Deposit destinationDeposit = depositRepository.findByDepositNumber(message.getDestinationNumber())
                .orElseThrow(() -> new NotFoundException(Deposit.class, message.getDestinationNumber()));
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
            log.error("Withdrawal failed (source deposit has not enough money), transfer request message: {}", message);
            return false;
        }
    }

    @Transactional
    public Boolean replenishDeposit(RequestKafkaTransferMessage message) {
        log.info("Trying replenish deposit using message: {}", message);

        Deposit destinationDeposit = depositRepository.findByDepositNumber(message.getDestinationNumber())
                .orElseThrow(() -> new NotFoundException(Deposit.class, message.getDestinationNumber()));

        destinationDeposit.setAmount(destinationDeposit.getAmount() + message.getAmount());

        log.info("Saving deposit after transfer {}", destinationDeposit);
        depositRepository.save(destinationDeposit);

        log.info("Replenishment successful for message: {}", message);
        return true;
    }

    @Transactional
    public Boolean withdrawalDeposit(RequestKafkaTransferMessage message) {
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
            log.error("Withdrawal failed (source deposit has not enough money), transfer request message: {}", message);
            return false;
        }
    }

    private Boolean isFirstTransferAttempt(UUID transferId) {
        if (transferRepository.findById(transferId).isPresent()) {
            return false;
        }
        return true;
    }

    private ResponseKafkaTransferMessage createAndSendResponse(Transfer transfer) {
        log.info("Creating response message based on result of transfer: {}", transfer);

        ResponseKafkaTransferMessage response = new ResponseKafkaTransferMessage();

        response.setTransferId(transfer.getTransferId());
        response.setResult(transfer.getResult());
        response.setStatusDescription(transfer.getStatusDescription());

        log.info("Sending response message with transfer result to Transfer service: {}", response);
        transferMoneyServiceKafkaResponseProducer.sendResponse(response);

        return response;
    }
}
