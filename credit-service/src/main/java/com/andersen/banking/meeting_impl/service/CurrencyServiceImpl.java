package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.service.CurrencyService;
import com.andersen.banking.meeting_db.entity.Currency;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_impl.exception.CurrencyNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    @Transactional(readOnly = true)
    public Currency getCurrencyById(UUID id) {

        log.info("Find currency by id: {}", id);

        var currency = currencyRepository.findById(id).
            orElseThrow(() -> new CurrencyNotFoundException(id));

        log.info("Currency with id {} successfully found", id);

        return currency;
    }
}
