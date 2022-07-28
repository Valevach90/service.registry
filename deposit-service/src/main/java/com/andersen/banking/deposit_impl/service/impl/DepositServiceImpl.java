package com.andersen.banking.deposit_impl.service.impl;

import com.andersen.banking.deposit_api.dto.kafka.TransferKafkaMessageDto;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_db.entities.TransferKafkaMessage;
import com.andersen.banking.deposit_db.repositories.DepositRepository;
import com.andersen.banking.deposit_db.repositories.TransferKafkaMessageRepository;
import com.andersen.banking.deposit_impl.config.KafkaConfigProperties;
import com.andersen.banking.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit_impl.mapping.TransferKafkaMessageMapper;
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

    private TransferKafkaMessageRepository messageRepository;

    private KafkaConfigProperties kafkaProperties;

    private KafkaTemplate<String, TransferKafkaMessageDto> kafkaTemplate;

    private TransferKafkaMessageMapper messageMapper;

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
    public void makeTransfer(TransferKafkaMessage message) {
        log.info("Trying make transfer for transfer message: {}", message);

        if (message.getDestinationType().equals("Deposit")) {

            String transferStatus = replenishDeposit(message);
            message.setStatusName(transferStatus);
        }

        if (message.getSourceType().equals("Deposit") && message.getStatusName().equals("Success")) {

            String transferStatus = withdrawalDeposit(message);
            message.setStatusName(transferStatus);
        }

        log.info("Saving transfer message: {}", message);
        messageRepository.save(message);

        log.info("Sending transfer message to Transfer service: {}", message);
        kafkaTemplate.send(kafkaProperties.getTopicName(), messageMapper.toTransferKafkaMessageDto(message));
    }

    private String replenishDeposit(TransferKafkaMessage message) {
        log.info("Trying replenish deposit using transfer message: {}", message);

        Optional<Deposit> destinationDeposit = depositRepository.findByDepositNumber(message.getDestinationNumber());

        if (destinationDeposit.isPresent()) {

            destinationDeposit.get().setAmount(destinationDeposit.get().getAmount() + message.getAmount());

            depositRepository.save(destinationDeposit.get());

            log.info("Replenishment successful for transfer message: {}", message);
           return "Success";
        }
        log.info("Withdrawal failed (deposit not found) for transfer message: {}", message);
        return "Fail";
    }

    private String withdrawalDeposit(TransferKafkaMessage message) {
        log.info("Trying withdrawal deposit using transfer message: {}", message);

        Optional<Deposit> sourceDeposit = depositRepository.findByDepositNumber(message.getSourceNumber());

        if (sourceDeposit.isPresent() && sourceDeposit.get().getAmount() >= message.getAmount()) {

            sourceDeposit.get().setAmount(sourceDeposit.get().getAmount() - message.getAmount());

            depositRepository.save(sourceDeposit.get());

            log.info("Withdrawal successful for transfer message: {}", message);
            return "Success";
        }
        log.info("Withdrawal failed (deposit not found or not enough money) for transfer message: {}", message);
        return "Fail";
    }
}
