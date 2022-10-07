package com.andersen.banking.meeting_impl.scheduling;

import com.andersen.banking.meeting_db.entities.Deposit;
import com.andersen.banking.meeting_db.repositories.DepositRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.service.DepositService;
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

    private final DepositService depositService;

    private final DepositInterestAccrualScheduler scheduler;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getMergedJobDataMap();

        UUID depositId = (UUID) jobDataMap.get(scheduler.JOB_KEY_OF_INTEREST_ACCRUAL);
        Long startedPeriodAmount = (Long) jobDataMap.get(scheduler.JOB_KEY_OF_STARTED_PERIOD_AMOUNT);

        log.info("Trying to perform scheduled interest accrual for deposit with id: {}", depositId);
        Deposit deposit = depositService.accrualDepositInterest(depositId, startedPeriodAmount);

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
