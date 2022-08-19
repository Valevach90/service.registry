package com.andersen.banking.deposit_impl.service.impl;
import com.andersen.banking.deposit_api.dto.messages.AccruedAmount;
import com.andersen.banking.deposit_db.entities.Deposit;
import com.andersen.banking.deposit_db.repositories.DepositRepository;
import com.andersen.banking.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit_impl.kafka.KafkaProducer;
import com.andersen.banking.deposit_impl.service.DepositService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;
    private final KafkaProducer kafkaProducer;

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
    public Page<Deposit> findDepositByUserId(Long userId, Pageable pageable) {
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
    public void deleteById(Long id) {
        log.info("Deleting deposit with id: {}", id);

        Deposit foundDeposit = depositRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Deposit.class, id));

        depositRepository.deleteById(id);

        log.info("Deleted deposit: {}", foundDeposit);
    }

    @Override
    @Scheduled(cron = "0 1 10 00 * ?", zone = "Europe/Moscow")
    public void interestCalculation(Deposit deposit) {
        log.info("Interest on the balance for the user with id : {}", deposit.getUserId());

        Long amount = deposit.getAmount();
        Long interestRate = deposit.getInterestRate().longValue();
        Long accrued = (amount*interestRate)/100;

        AccruedAmount forTransfer = new AccruedAmount();

        forTransfer.setUserId(deposit.getUserId());
        forTransfer.setAmount(amount);
        forTransfer.setInterestRate(interestRate);
        forTransfer.setAccrued(accrued);
        forTransfer.setCurrency(deposit.getCurrency().getName());
        Future<RecordMetadata> future =
                kafkaProducer.sendMessage("interest_calculation", forTransfer);

        log.info("Sending interest on the balance for the user with id : {}", deposit.getUserId());
    }

}
