package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.message.TransferKafkaDeposit;
import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;

import java.util.List;

public interface TransferMoneyService {

    List<CurrencyResponseDto> getAllCurrencies();

    List<PaymentTypeResponseDto> getAllPaymentTypes();

    TransferStatusResponseDto getTransferStatus(Long transferId);

    void createTransferForAccruedAmount(TransferKafkaDeposit transfer);

}
