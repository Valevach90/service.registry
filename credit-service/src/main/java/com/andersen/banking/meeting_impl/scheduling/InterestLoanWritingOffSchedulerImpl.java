package com.andersen.banking.meeting_impl.scheduling;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
public class InterestLoanWritingOffSchedulerImpl implements InterestLoanWritingOffScheduler {

    private final Scheduler scheduler;

    @Override
    public void scheduleInterestLoanWritingOff(UUID creditId, Long amountOfInterestLoanWritingOff) {
        log.info("Trying to schedule interest loan writing off for credit with id :{}", creditId);

        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put(JOB_KEY_OF_INTEREST_LOAN_WRITING_OFF, creditId);
        jobDataMap.put(JOB_KEY_OF_AMOUNT_OF_INTEREST_LOAN_WRITING_OFF, amountOfInterestLoanWritingOff);

        JobDetail jobDetail = JobBuilder.newJob()
                .withIdentity(UUID.randomUUID().toString())
                .setJobData(jobDataMap)
                .withDescription("Writing of interest loan for credit with id: " + creditId)
                .ofType(InterestLoanWritingOffExecutor.class)
                .storeDurably()
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startAt(Date.from(Instant.now().plus(PERIOD_IF_INTEREST_LOAN_WRITING_OFF, ChronoUnit.DAYS)))
                .withDescription("Writing of interest loan for credit with id: " + creditId)
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("Scheduled interest loan writing off for credit with id: {}, trigger: {}", creditId, trigger);

        } catch (SchedulerException message) {
            log.error("Scheduling for credit with id {} with trigger {} failed with message {}", creditId, trigger, message);
            throw new RuntimeException("Scheduling of interest loan writing off афшдув for credit with id " + creditId);
        }
    }
}
