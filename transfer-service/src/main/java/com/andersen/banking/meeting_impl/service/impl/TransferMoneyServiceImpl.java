package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_db.repository.PaymentTypeRepository;
import com.andersen.banking.meeting_db.repository.TransferStatusRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.CurrencyMapper;
import com.andersen.banking.meeting_impl.mapper.PaymentTypeMapper;
import com.andersen.banking.meeting_impl.mapper.TransferStatusMapper;
import com.andersen.banking.meeting_impl.service.TransferMoneyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferMoneyServiceImpl implements TransferMoneyService {

    private final CurrencyRepository currencyRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final TransferStatusRepository transferStatusRepository;

    private final CurrencyMapper currencyMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final TransferStatusMapper transferStatusMapper;

    @Override
    @Cacheable("currencies")
    public List<CurrencyResponseDto> getAllCurrencies() {
        log.debug("Get currencies");
        return currencyRepository.findAll()
                .stream().map(currencyMapper::currency2CurrencyResponseDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable("paymentTypes")
    public List<PaymentTypeResponseDto> getAllPaymentTypes() {
        log.debug("Get paymentTypes");
        return paymentTypeRepository.findAll()
                .stream().map(paymentTypeMapper::paymentType2PaymentTypeResponseDto).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public TransferStatusResponseDto getTransferStatus(Long transferId) {
        log.debug("Get transferStatus by id : {}", transferId);

        TransferStatus transferStatus = transferStatusRepository.findById(transferId)
                .orElseThrow(() -> new NotFoundException(TransferStatus.class, transferId));
        
        return transferStatusMapper.transferStatus2TransferStatusResponseDto(transferStatus);
    }

}
