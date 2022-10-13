package com.andersen.banking.meeting_impl.service.depositScheduler;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.repositories.DepositRepository;
import com.andersen.banking.meeting_impl.service.ClosedDepositTransferService;
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

    private final ClosedDepositTransferService closedDepositTransferService;

    @Scheduled(cron = "${scheduler.deposit}")
    public void closingDepositsScheduling() {
        log.info("START SCHEDULING");
        closedDepositTransferService.closingDeposits();
    }
}
