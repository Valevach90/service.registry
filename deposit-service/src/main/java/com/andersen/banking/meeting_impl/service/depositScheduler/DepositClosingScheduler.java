package com.andersen.banking.meeting_impl.service.depositScheduler;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.repositories.DepositRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class DepositClosingScheduler {

    private final DepositRepository depositRepository;

    @Transactional
    @Scheduled(cron = "${scheduler.deposit}")
    public void closingDepositsScheduling() {
        List<Deposit> list;
        do {
            Pageable page = Pageable.ofSize(1000);
            list = depositRepository.closingScheduler(page);
            list.forEach(deposit -> deposit.setIsActive(false));
            log.info("deposits with the current closing date are closed");
            depositRepository.saveAll(list);
        } while (!list.isEmpty());
    }
}