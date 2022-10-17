package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.DepositType;
import java.util.List;

/**
 * Service for working with types Deposit Products.
 */

public interface DepositTypeService {

    /**
     * Fina all currencies
     *
     * @return
     */
    List<DepositType> findAll();
}
