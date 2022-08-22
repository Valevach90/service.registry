package com.andersen.banking.meeting_impl.util.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.PaymentTypeService;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferRequestPaymentTypeValidator implements TransferRequestValidator {

    private final PaymentTypeService paymentTypeService;

    /**
     * @param transferRequestDto
     */
    @Override
    @Transactional(readOnly = true)
    public void validate(TransferRequestDto transferRequestDto) throws NotFoundException {
        log.info("Validating on payment type for : {}", transferRequestDto);

        paymentTypeService.getPaymentTypeById(transferRequestDto.getSourcePaymentTypeId());
        paymentTypeService.getPaymentTypeById(transferRequestDto.getDestinationPaymentTypeId());

        log.info("Validated on payment type for : {}", transferRequestDto);
    }
}
