package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_db.entity.PaymentType;
import com.andersen.banking.meeting_db.repository.PaymentTypeRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.PaymentTypeMapper;
import com.andersen.banking.meeting_impl.service.PaymentTypeService;
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
@CacheConfig(cacheNames = {"paymentTypes"})
@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeMapper paymentTypeMapper;

    private final PaymentTypeRepository paymentTypeRepository;


    @Override
    @Cacheable
    @Transactional(readOnly = true)
    public List<PaymentTypeResponseDto> getAllPaymentTypes() {
        log.debug("Get paymentTypes");
        return paymentTypeRepository.findAll().stream().map(paymentTypeMapper::paymentType2PaymentTypeResponseDto).toList();
    }

    @Override
    @Cacheable
    @Transactional(readOnly = true)
    public PaymentType getPaymentTypeById(UUID id) {
        return paymentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(PaymentType.class, id));
    }
}