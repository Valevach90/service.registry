package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.RegularPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * This method finds all Regular Payments;
     *
     * @param
     * @return
     */
    Page<RegularPayment> findAll(Pageable pageable);

    /**
     * This method updates Regular Payment.
     *
     * @param regularPayment
     * @return
     */
    RegularPayment update(RegularPayment regularPayment);

    /**
     * This method deletes Regular Payment by id.
     *
     * @param id
     * @return
     */
    void deleteById(UUID id);

    /**
     * This method executes some amount of regular payments. (for scheduler. Multithreading friendly)
     *
     * @param
     * @return
     */
    boolean executeSomeAmountOfRegularPayments();
}
