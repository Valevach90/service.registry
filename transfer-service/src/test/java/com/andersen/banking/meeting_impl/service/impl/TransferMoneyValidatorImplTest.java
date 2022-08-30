package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;
import com.andersen.banking.meeting_impl.util.impl.TransferRequestCurrencyValidator;
import com.andersen.banking.meeting_impl.util.impl.TransferRequestPaymentTypeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferMoneyValidatorImplTest {

    @Mock
    TransferRequestDto transferRequestDto;

    @Mock
    List<TransferRequestValidator> requestValidators;

    @Mock
    TransferRequestCurrencyValidator transferRequestCurrencyValidator;

    @Mock
    TransferRequestPaymentTypeValidator transferRequestPaymentTypeValidator;

    @InjectMocks
    TransferMoneyValidatorImpl transferMoneyValidator;

    @BeforeEach
    void initRequestValidators() {
        requestValidators = List.of(transferRequestCurrencyValidator, transferRequestPaymentTypeValidator);
    }

    @Test
    void validate_ShouldReturnTrue_WhenAllValidatorsReturnTrue() {

        final boolean isValid = true;
        when(transferRequestCurrencyValidator.validate(transferRequestDto)).thenReturn(isValid);
        when(transferRequestPaymentTypeValidator.validate(transferRequestDto)).thenReturn(isValid);

        final boolean actual = transferMoneyValidator.validate(transferRequestDto, requestValidators);

        assertNotNull(actual);
        assertEquals(isValid, actual);
    }

    @Test
    void validate_ShouldReturnFalse_WhenAllValidatorsReturnFalse() {

        final boolean isValid = false;
        when(transferRequestCurrencyValidator.validate(transferRequestDto)).thenReturn(isValid);
        when(transferRequestPaymentTypeValidator.validate(transferRequestDto)).thenReturn(isValid);

        final boolean actual = transferMoneyValidator.validate(transferRequestDto, requestValidators);

        assertNotNull(actual);
        assertEquals(isValid, actual);
    }

    @Test
    void validate_ShouldReturnFalse_WhenAtLeastOneOfValidatorsReturnFalse() {

        final boolean isValid = false;
        when(transferRequestCurrencyValidator.validate(transferRequestDto)).thenReturn(!isValid);
        when(transferRequestPaymentTypeValidator.validate(transferRequestDto)).thenReturn(isValid);

        final boolean actual = transferMoneyValidator.validate(transferRequestDto, requestValidators);

        assertNotNull(actual);
        assertEquals(isValid, actual);
    }

}