package com.andersen.banking.meeting_impl.scheduling;

import com.andersen.banking.meeting_db.entities.Deposit;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepositInterestAccrualScheduler {

    public static final Integer PERIOD_IF_INTEREST_ACCRUAL = 30;
    public static final String JOB_KEY_OF_INTEREST_ACCRUAL = "depositId";
    public static final String JOB_KEY_OF_STARTED_PERIOD_AMOUNT = "startedDepositAmount";

    private final Scheduler scheduler;

    public void scheduleDepositInterestAccrual(Deposit deposit) {
        log.info("Trying to schedule accrual of interest for deposit :{}", deposit.toString());

        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put(JOB_KEY_OF_INTEREST_ACCRUAL, deposit.getId());
        jobDataMap.put(JOB_KEY_OF_STARTED_PERIOD_AMOUNT, deposit.getAmount());

        JobDetail jobDetail = JobBuilder.newJob()
                .withIdentity(UUID.randomUUID().toString())
                .setJobData(jobDataMap)
                .withDescription("Accrual of interest for deposit: " + deposit.getId())
                .ofType(DepositInterestAccrualExecutor.class)
                .storeDurably()
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startAt(Date.from(Instant.now().plus(PERIOD_IF_INTEREST_ACCRUAL, ChronoUnit.DAYS)))
                .withDescription("Accrual of interest for deposit: " + deposit.getId())
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("Scheduled accrual of interest for deposit: {}", deposit);

        } catch (SchedulerException message) {
            log.error("Scheduling for deposit {} failed with message {}", deposit, message);
            throw new RuntimeException("Scheduling of interest accrual failed for deposit with id " + deposit.getId());
        }
    }
}
