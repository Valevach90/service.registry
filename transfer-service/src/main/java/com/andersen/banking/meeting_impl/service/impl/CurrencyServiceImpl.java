package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_db.entity.Currency;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.CurrencyMapper;
import com.andersen.banking.meeting_impl.service.CurrencyService;
import java.util.List;
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

    private final CurrencyMapper currencyMapper;


    @Override
    @Transactional(readOnly = true)
    public List<CurrencyResponseDto> getAllCurrencies() {
        log.debug("Get currencies");
        return currencyRepository.findAll()
                .stream().map(currencyMapper::currency2CurrencyResponseDto).toList();
    }


    @Override
    @Transactional(readOnly = true)
    public Currency getCurrencyById(UUID id) throws NotFoundException {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Currency.class, id));
    }
}
