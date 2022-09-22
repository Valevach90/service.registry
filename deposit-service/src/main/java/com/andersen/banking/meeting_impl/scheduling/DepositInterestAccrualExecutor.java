package com.andersen.banking.meeting_impl.scheduling;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.repositories.DepositRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Executor for deposit interest accrual.
 */

@Slf4j
@RequiredArgsConstructor
public class DepositInterestAccrualExecutor implements Job {

    private final DepositRepository depositRepository;

    private final DepositInterestAccrualScheduler scheduler;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getMergedJobDataMap();

        UUID depositId = (UUID) jobDataMap.get(scheduler.JOB_KEY_OF_INTEREST_ACCRUAL);
        Long startedPeriodAmount = (Long) jobDataMap.get(scheduler.JOB_KEY_OF_STARTED_PERIOD_AMOUNT);

        log.info("Trying to perform scheduled interest accrual for deposit with id: {}", depositId);
        accrualDepositInterest(depositId, startedPeriodAmount);
    }

    @Transactional
    public void accrualDepositInterest(UUID depositId, Long startedPeriodAmount) {

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

        scheduleTheNextInterestAccrual(deposit);
    }

    private void scheduleTheNextInterestAccrual(Deposit deposit){

        log.info("Trying to schedule the next interest accrual for deposit {}", deposit);

        if (deposit.getCloseDate().compareTo(
                Date.from(Instant.now().plus(scheduler.PERIOD_IF_INTEREST_ACCRUAL, ChronoUnit.DAYS))) >= 0){

            scheduler.scheduleDepositInterestAccrual(deposit);
        } else {
            log.info("Deposit close date is less than interest accrual period, deposit {}", deposit);
        }
    }
}
