package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.Currency;
import com.andersen.banking.meeting_db.repositories.CurrencyRepository;
import com.andersen.banking.meeting_impl.service.CurrenciesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrenciesServiceImpl implements CurrenciesService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> findAll() {
        log.debug("Get list of currencies.");
        return currencyRepository.findAll();
    }
}
