package com.andersen.banking.meeting_impl.scheduling;

import com.andersen.banking.meeting_api.service.CreditService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
@RequiredArgsConstructor
public class InterestLoanWritingOffExecutor implements Job {

    private final CreditService creditService;

    private final InterestLoanWritingOffScheduler scheduler;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


        JobDataMap jobDataMap = context.getMergedJobDataMap();

        UUID creditId = (UUID) jobDataMap.get(scheduler.JOB_KEY_OF_INTEREST_LOAN_WRITING_OFF);
        Long amountOfInterestLoanWritingOff = (Long) jobDataMap.get(scheduler.JOB_KEY_OF_AMOUNT_OF_INTEREST_LOAN_WRITING_OFF);

        log.info("Trying to perform scheduled interest loan writing off for credit with id: {}", creditId);
        creditService.writeOffInterestLoan(creditId, amountOfInterestLoanWritingOff);

        scheduleTheNextInterestAccrual(creditId);
    }

    private void scheduleTheNextInterestAccrual(UUID creditId){

        log.info("Trying to schedule the next interest loan writing off for credit with id {}", creditId);

        Long amountOfTheNextInterestLoanWritingOff = creditService.getAmountOfTheNextInterestLoanWritingOff(creditId);

        if (amountOfTheNextInterestLoanWritingOff != 0){
            scheduler.scheduleInterestLoanWritingOff(creditId, amountOfTheNextInterestLoanWritingOff);

        } else {
            log.info("Loan repaid for credit with id {}", creditId);
        }
    }
}
