package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_impl.service.TransferMoneyValidator;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class TransferMoneyValidatorImpl implements TransferMoneyValidator {


    @Override
    @Transactional
    public boolean validate(TransferRequestDto transferRequestDto, List<TransferRequestValidator> requestValidators) {
        log.info("Validating transfer request using N = {} validators", requestValidators.size());
        boolean isValid = false;

        for (TransferRequestValidator requestValidator : requestValidators) {
            isValid = requestValidator.validate(transferRequestDto);
        }

        log.info("Validated by validators, result is : {}", isValid);
        return isValid;
    }
}
