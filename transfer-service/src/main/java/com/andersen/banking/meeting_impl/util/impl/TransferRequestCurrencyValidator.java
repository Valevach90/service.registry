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
    public void validate(TransferRequestDto transferRequestDto) throws NotFoundException {
        log.info("Validating on currency for : {}", transferRequestDto);

        currencyService.getCurrencyById(transferRequestDto.getCurrencyId());

        log.info("Validated on currency for : {}", transferRequestDto);
    }
}
