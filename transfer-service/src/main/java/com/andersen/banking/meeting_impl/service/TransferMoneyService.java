package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;

import java.util.List;

public interface TransferMoneyService {

    List<CurrencyResponseDto> getAllCurrencies();

    List<PaymentTypeResponseDto> getAllPaymentTypes();

}