package com.andersen.banking.deposit_impl.service.impl;

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

    private final DepositRepository depositRepository;

    private final TransferRepository transferRepository;

    private KafkaConfigProperties kafkaProperties;

    private KafkaTemplate<String, ResponseKafkaTransferMessage> kafkaTemplate;

    private TransferMapper transferMapper;

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
    public void makeTransfer(Transfer transfer) {
        log.info("Trying make transfer: {}", transfer);

        if (transfer.getDestinationType().equals("Deposit")) {

            Boolean replenishResult = replenishDeposit(transfer);
            transfer.setResult(replenishResult);
        }

        if (transfer.getSourceType().equals("Deposit") && transfer.getResult()) {

            Boolean withdrawalResult = withdrawalDeposit(transfer);
            transfer.setResult(withdrawalResult);
        }

        log.info("Saving transfer: {}", transfer);
        transferRepository.save(transfer);
        //transferRepository.deleteAll();

        //create response message
        log.info("Sending response message with transfer result to Transfer service: {}", transfer);
        //kafkaTemplate.send(kafkaProperties.getTopicName(), messageMapper.toTransferKafkaMessageDto(message));
    }

    private Boolean replenishDeposit(Transfer transfer) {
        log.info("Trying replenish deposit using transfer: {}", transfer);

        Optional<Deposit> destinationDeposit = depositRepository.findByDepositNumber(transfer.getDestinationNumber());

        if (destinationDeposit.isPresent()) {

            destinationDeposit.get().setAmount(destinationDeposit.get().getAmount() + transfer.getAmount());

            depositRepository.save(destinationDeposit.get());

            log.info("Replenishment successful for transfer: {}", transfer);
           return true;
        }
        log.info("Withdrawal failed (deposit not found) for transfer: {}", transfer);
        return false;
    }

    private Boolean withdrawalDeposit(Transfer transfer) {
        log.info("Trying withdrawal deposit using transfer: {}", transfer);

        Optional<Deposit> sourceDeposit = depositRepository.findByDepositNumber(transfer.getSourceNumber());

        if (sourceDeposit.isPresent() && sourceDeposit.get().getAmount() >= transfer.getAmount()) {

            sourceDeposit.get().setAmount(sourceDeposit.get().getAmount() - transfer.getAmount());

            depositRepository.save(sourceDeposit.get());

            log.info("Withdrawal successful for transfer: {}", transfer);
            return true;
        }
        log.info("Withdrawal failed (deposit not found or not enough money) for transfer: {}", transfer);
        return false;
    }
}
