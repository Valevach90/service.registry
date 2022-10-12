package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.Currency;
import java.util.List;

/**
 * Service for working with currencies.
 */

public interface CurrenciesService {

    /**
     * Fina all currencies
     *
     * @return - list of currencies
     */
    List<Currency> findAll();
}
