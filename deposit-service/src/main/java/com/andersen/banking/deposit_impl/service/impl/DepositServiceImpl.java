package com.andersen.banking.deposit_impl.service.impl;

import com.andersen.banking.deposit_api.dto.kafka.RequestTransferKafkaMessage;
import com.andersen.banking.deposit_api.dto.kafka.ResponseKafkaTransferMessage;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_db.entities.Transfer;
import com.andersen.banking.deposit_db.repositories.DepositRepository;
import com.andersen.banking.deposit_db.repositories.TransferRepository;
import com.andersen.banking.deposit_impl.config.KafkaConfigProperties;
import com.andersen.banking.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit_impl.mapping.TransferMapper;
import com.andersen.banking.deposit_impl.service.DepositService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private static final String TRANSFER_WITH_DEPOSIT_TYPE = "Deposit";

    private final DepositRepository depositRepository;

    private final TransferRepository transferRepository;

    private final KafkaConfigProperties kafkaProperties;

    private final KafkaTemplate<String, ResponseKafkaTransferMessage> kafkaTemplate;

    private final TransferMapper transferMapper;

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
    public Optional<Deposit> findById(Long id) {
        log.info("Find deposit by id: {}", id);

        Optional<Deposit> deposit = depositRepository.findById(id);

        log.info("Found deposit by id: {}", deposit);
        return deposit;
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
    public void deleteById(Long id) {
        log.info("Deleting deposit with id: {}", id);

        Deposit foundDeposit = depositRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Deposit.class, id));

        depositRepository.deleteById(id);

        log.info("Deleted deposit: {}", foundDeposit);
    }

    @Override
    @Transactional
    public void makeTransfer(RequestTransferKafkaMessage message) {
        log.info("Trying make transfer using message: {}", message);

        if (isFirstTransferAttempt(message.getTransferId())) {
            Transfer transfer = transferMapper.toTransfer(message);

            if (message.getDestinationType().equals(TRANSFER_WITH_DEPOSIT_TYPE)) {
                transfer.setResult(replenishDeposit(message));
            }

            if (transfer.getResult() && message.getSourceType().equals(TRANSFER_WITH_DEPOSIT_TYPE)) {
                transfer.setResult(withdrawalDeposit(message));
            }

            log.info("Saving transfer: {}", transfer);
            transferRepository.save(transfer);

            log.info("Sending response message: {}", transfer);
            sendResponse(transfer);

        } else {
            log.info("Transfer with id equal to transfer id from request message {} already exists", message);
        }
    }

    private Boolean replenishDeposit(RequestTransferKafkaMessage message) {
        log.info("Trying replenish deposit using message: {}", message);

        Optional<Deposit> destinationDeposit = depositRepository.findByDepositNumber(message.getDestinationNumber());

        if (destinationDeposit.isPresent()) {

            destinationDeposit.get().setAmount(destinationDeposit.get().getAmount() + message.getAmount());

            depositRepository.save(destinationDeposit.get());

            log.info("Replenishment successful for message: {}", message);
            return true;
        } else {
            log.info("Withdrawal failed (deposit not found) for message: {}", message);
            return false;
        }
    }

    private Boolean withdrawalDeposit(RequestTransferKafkaMessage message) {
        log.info("Trying withdrawal deposit using message: {}", message);

        Optional<Deposit> sourceDeposit = depositRepository.findByDepositNumber(message.getSourceNumber());

        if (sourceDeposit.isPresent() && sourceDeposit.get().getAmount() >= message.getAmount()) {

            sourceDeposit.get().setAmount(sourceDeposit.get().getAmount() - message.getAmount());

            depositRepository.save(sourceDeposit.get());

            log.info("Withdrawal successful for message: {}", message);
            return true;
        } else {
            log.info("Withdrawal failed (deposit not found or not enough money) for message: {}", message);
            return false;
        }
    }

    private Boolean isFirstTransferAttempt(Long transferId) {
        if (transferRepository.findById(transferId).isPresent()) {
            return false;
        }
        return true;
    }

    private void sendResponse(Transfer transfer) {
        log.info("Creating response message based on result of transfer: {}", transfer);

        ResponseKafkaTransferMessage response = new ResponseKafkaTransferMessage();

        response.setTransferId(transfer.getTransferId());
        response.setResult(transfer.getResult());
        response.setStatusDescription(transfer.getStatusDescription());

        log.info("Sending response message with transfer result to Transfer service: {}", response);
        kafkaTemplate.send(kafkaProperties.getTopicTransferResponse(), response);
    }
}
