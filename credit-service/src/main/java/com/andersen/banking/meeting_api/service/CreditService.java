package com.andersen.banking.meeting_api.service;

import java.util.UUID;

public interface CreditService {

    /**
     * Write off interest loan for credit
     *
     * @param creditId id of credit to write off interest loan
     * @param amountOfInterestLoanWritingOff amount for writing off of interest loan
     */
    void writeOffInterestLoan(UUID creditId, Long amountOfInterestLoanWritingOff);

    /**
     * Get amount for the next interest loan writing off
     *
     * @param creditId id of credit to write off interest loan
     * @return amount for the next writing off or 0 if loan repaid
     */
    Long getAmountOfTheNextInterestLoanWritingOff(UUID creditId);
}
