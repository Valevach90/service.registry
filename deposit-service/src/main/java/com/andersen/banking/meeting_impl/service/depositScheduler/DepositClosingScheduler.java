package com.andersen.banking.meeting_impl.service.depositScheduler;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_impl.service.ClosedDepositTransferService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DepositClosingScheduler {

    private final ClosedDepositTransferService closedDepositTransferService;

    @Scheduled(cron = "${scheduler.deposit}")
    public void closingDepositsScheduling() {
        log.info("START SCHEDULING OF CLOSING DEPOSITS AND TRANSFER FUNDS TO CARD");

        List<Deposit> depositsForTransferringToCard = closedDepositTransferService.closingDeposits();

        if(!depositsForTransferringToCard.isEmpty()) {
            closedDepositTransferService.transferToAccount(depositsForTransferringToCard);
            closedDepositTransferService.resetAmountAfterTransferToCard(depositsForTransferringToCard);
        }

        log.info("FINISH SCHEDULING OF CLOSING DEPOSITS AND TRANSFER FUNDS TO CARD");
    }
}
