package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_impl.service.TransferExecutor;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransferManagerImplTest {

    private final static UUID paymentTypeId = UUID.randomUUID();

    @Mock
    List<TransferRequestValidator> validators;

    @Mock
    TransferMoneyValidatorImpl transferMoneyValidator;

    @Mock
    TransferRequestDto transferRequestDto;

    @Mock
    TransferResponseDto transferResponseDto;

    @InjectMocks
    TransferManagerImpl transferManager;


    @Mock
    TransferExecutorImpl transferExecutorImpl;

    @Test
    void run_ShouldReturnResponseDto_WhenMessageSent() {
        when(transferRequestDto.getSourcePaymentTypeId()).thenReturn(paymentTypeId);
        when(transferRequestDto.getDestinationPaymentTypeId()).thenReturn(paymentTypeId);
        when(transferMoneyValidator.validate(transferRequestDto, validators)).thenReturn(true);
        when(transferExecutorImpl.execute(transferRequestDto)).thenReturn(transferResponseDto);

        TransferResponseDto actual = transferManager.run(transferRequestDto);

        assertNotNull(actual);
        assertEquals(transferResponseDto, actual);
    }

    @Test
    void run_ShouldThrowRuntimeException_WhenGetExecutorMethodThrowRuntimeException() {
        when(transferRequestDto.getSourcePaymentTypeId()).thenReturn(paymentTypeId);
        when(transferRequestDto.getDestinationPaymentTypeId()).thenReturn(UUID.randomUUID());
        when(transferMoneyValidator.validate(transferRequestDto, validators)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> transferManager.run(transferRequestDto));
    }


    @Test
    void getExecutor_ShouldReturnInternalTransferExecutor_WhenSourceAndDestinationPaymentTypeIdIsEquals() {
        when(transferRequestDto.getSourcePaymentTypeId()).thenReturn(paymentTypeId);
        when(transferRequestDto.getDestinationPaymentTypeId()).thenReturn(paymentTypeId);

        TransferExecutor transferExecutor = transferManager.getExecutor(transferRequestDto);

        assertNotNull(transferExecutor);
        assertEquals(transferExecutor, transferExecutorImpl);
    }

    @Test
    void getExecutor_ShouldThrowRuntimeException_WhenSourceAndDestinationPaymentTypeIdIsNotEquals() {
        when(transferRequestDto.getSourcePaymentTypeId()).thenReturn(paymentTypeId);
        when(transferRequestDto.getDestinationPaymentTypeId()).thenReturn(UUID.randomUUID());

        assertThrows(RuntimeException.class, () -> transferManager.getExecutor(transferRequestDto));
    }

}