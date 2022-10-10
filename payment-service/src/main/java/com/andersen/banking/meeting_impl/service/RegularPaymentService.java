package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.RegularPayment;

import java.util.UUID;

public interface RegularPaymentService {

    /**
     * This method creates new Regular Payment.
     *
     * @param regularPayment
     * @return
     */
    RegularPayment create(RegularPayment regularPayment);

    /**
     * This method finds Regular Payment by Id;
     *
     * @param id
     * @return
     */
    RegularPayment findById(UUID id);

    /**
     * This method updates Regular Payment.
     *
     * @param regularPayment
     * @return
     */
    RegularPayment update(RegularPayment regularPayment);
}
