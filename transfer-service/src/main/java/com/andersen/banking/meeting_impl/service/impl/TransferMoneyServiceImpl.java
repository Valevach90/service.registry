package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_db.repository.PaymentTypeRepository;
import com.andersen.banking.meeting_impl.mapper.CurrencyMapper;
import com.andersen.banking.meeting_impl.mapper.PaymentTypeMapper;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyServiceImpl implements TransferMoneyService {

    private final CurrencyRepository currencyRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    private final CurrencyMapper currencyMapper;
    private final PaymentTypeMapper paymentTypeMapper;

    @Override
    @Cacheable("currencies")
    public List<CurrencyResponseDto> getAllCurrencies() {
        log.debug("Get currencies");
        return currencyRepository.findAll()
                .stream().map(currencyMapper::currency2CurrencyResponseDto).toList();
    }

    @Override
    @Cacheable("paymentTypes")
    public List<PaymentTypeResponseDto> getAllPaymentTypes() {
        log.debug("Get paymentTypes");
        return paymentTypeRepository.findAll()
                .stream().map(paymentTypeMapper::paymentType2PaymentTypeResponseDto).toList();

    }

}