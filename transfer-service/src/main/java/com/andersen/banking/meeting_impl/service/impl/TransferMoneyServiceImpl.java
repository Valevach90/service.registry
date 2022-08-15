package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_db.repository.PaymentTypeRepository;
import com.andersen.banking.meeting_db.repository.TransferRepository;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.CurrencyMapper;
import com.andersen.banking.meeting_impl.mapper.PaymentTypeMapper;
import com.andersen.banking.meeting_impl.mapper.TransferMapper;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapper;
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
    private final TransferRepository transferRepository;
    private final TransferStatusRepository transferStatusRepository;

    private final CurrencyMapper currencyMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final TransferMapper transferMapper;
    private final TransferStatusMapper transferStatusMapper;

    @Override
    @Cacheable("transfers")
    public List<TransferResponseDto> findAllByUserId(Long userId) {
        log.debug("Get transfers for user {}", userId);
        return transferRepository.findTransfersByUserId(userId)
                .stream().map(transferMapper::transfer2transferResponseDto).toList();
    }

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

    @Override
    public TransferStatusResponseDto getTransferStatus(Long transferId) {
        log.debug("Get transferStatus by id : {}", transferId);

        TransferStatus transferStatus = transferStatusRepository.findById(transferId)
                .orElseThrow(() -> new NotFoundException(TransferStatus.class, transferId));
        
        return transferStatusMapper.transferStatus2TransferStatusResponseDto(transferStatus);
    }

}
