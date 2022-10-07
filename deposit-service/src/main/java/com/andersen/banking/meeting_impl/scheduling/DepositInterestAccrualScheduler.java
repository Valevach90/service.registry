package com.andersen.banking.meeting_impl.scheduling;

import com.andersen.banking.meeting_db.entities.Deposit;

/**
 * Scheduler for deposit interest accrual planning.
 */

public interface DepositInterestAccrualScheduler {

    Integer PERIOD_IF_INTEREST_ACCRUAL = 30;
    String JOB_KEY_OF_INTEREST_ACCRUAL = "depositId";
    String JOB_KEY_OF_STARTED_PERIOD_AMOUNT = "startedDepositAmount";

    /**
     * Schedule accrual of deposit interest.
     *
     * @param deposit deposit to schedule of interest accrual
     */
    void scheduleDepositInterestAccrual(Deposit deposit);
}
