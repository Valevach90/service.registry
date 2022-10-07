package com.andersen.banking.meeting_impl.scheduling;

import java.util.UUID;

/**
 * Scheduler for interest loan writing off planning.
 */

public interface InterestLoanWritingOffScheduler {

    Integer PERIOD_IF_INTEREST_LOAN_WRITING_OFF = 30;
    String JOB_KEY_OF_INTEREST_LOAN_WRITING_OFF = "depositId";
    String JOB_KEY_OF_AMOUNT_OF_INTEREST_LOAN_WRITING_OFF = "amountOfInterestLoanWritingOff";

    /**
     * Schedule interest loan writing off for credit.
     *
     * @param creditId id of credit to schedule interest loan writing off
     * @param amountOfInterestLoanWritingOff amount of interest loan for the next writing off
     */
    void scheduleInterestLoanWritingOff(UUID creditId, Long amountOfInterestLoanWritingOff);
}
