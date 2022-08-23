package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_db.entity.Currency;

import java.util.List;
import java.util.UUID;

public interface CurrencyService {

    /**
     * @return List of CurrencyResponseDto
     */
    List<CurrencyResponseDto> getAllCurrencies();

    /**
     * @param id
     * @return
     */
    Currency getCurrencyById(UUID id);

}
