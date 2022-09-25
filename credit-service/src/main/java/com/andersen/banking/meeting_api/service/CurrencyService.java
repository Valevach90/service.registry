package com.andersen.banking.meeting_api.service;

import com.andersen.banking.meeting_api.dto.CurrencyResponseDTO;
import com.andersen.banking.meeting_db.entity.Currency;
import java.util.UUID;

public interface CurrencyService {

    Currency getCurrencyById(UUID id);
}
