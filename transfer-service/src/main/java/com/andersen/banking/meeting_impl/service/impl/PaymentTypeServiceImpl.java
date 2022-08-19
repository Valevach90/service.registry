package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_db.entity.PaymentType;
import com.andersen.banking.meeting_db.repository.PaymentTypeRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.PaymentTypeMapper;
import com.andersen.banking.meeting_impl.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeMapper paymentTypeMapper;

    private final PaymentTypeRepository paymentTypeRepository;


    @Override
    @Cacheable("paymentTypes")
    @Transactional(readOnly = true)
    public List<PaymentTypeResponseDto> getAllPaymentTypes() {
        log.debug("Get paymentTypes");
        return paymentTypeRepository.findAll().stream().map(paymentTypeMapper::paymentType2PaymentTypeResponseDto).toList();
    }

    @Override
    public PaymentType getPaymentTypeById(Long id) {
        return paymentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(PaymentType.class, id));
    }
}
