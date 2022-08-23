package com.andersen.banking.meeting_impl.util.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.CurrencyService;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class TransferRequestCurrencyValidator implements TransferRequestValidator {

    private final CurrencyService currencyService;

    /**
     * @param transferRequestDto
     */
    @Override
    public boolean validate(TransferRequestDto transferRequestDto) {
        try {
            log.info("Validating on currency for : {}", transferRequestDto);

            currencyService.getCurrencyById(transferRequestDto.getCurrencyId());

            log.info("Validated on currency for : {}", transferRequestDto);

            return true;
        } catch (NotFoundException e) {
            log.info("Validation finished with exception :{}", e.getMessage());

            return false;
        }

    }
}
