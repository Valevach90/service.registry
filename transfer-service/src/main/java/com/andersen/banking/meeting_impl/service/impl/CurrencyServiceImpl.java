package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_db.entity.Currency;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.CurrencyMapper;
import com.andersen.banking.meeting_impl.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@CacheConfig(cacheNames = {"currencies"})
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;


    @Override
    @Cacheable
    @Transactional(readOnly = true)
    public List<CurrencyResponseDto> getAllCurrencies() {
        log.debug("Get currencies");
        return currencyRepository.findAll()
                .stream().map(currencyMapper::currency2CurrencyResponseDto).toList();
    }


    @Override
    @Cacheable
    @Transactional(readOnly = true)
    public Currency getCurrencyById(UUID id) throws NotFoundException {
        return currencyRepository.findById(id).orElseThrow(() -> new NotFoundException(Currency.class, id));
    }
}