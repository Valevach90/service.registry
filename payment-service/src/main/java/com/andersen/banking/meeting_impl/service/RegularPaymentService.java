package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.RegularPayment;

public interface RegularPaymentService {

    /**
     * This method creates new Regular Payment.
     *
     * @param regularPayment
     * @return
     */
    RegularPayment create (RegularPayment regularPayment);

}