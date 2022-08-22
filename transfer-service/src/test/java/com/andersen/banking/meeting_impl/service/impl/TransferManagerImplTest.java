package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_db.repository.PaymentTypeRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.TransferExecutor;
import com.andersen.banking.meeting_impl.util.TransferRequestValidator;
import com.andersen.banking.meeting_impl.util.impl.TransferRequestCurrencyValidator;
import com.andersen.banking.meeting_impl.util.impl.TransferRequestPaymentTypeValidator;
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
    private final static UUID currencyId = UUID.randomUUID();

    @Mock
    List<TransferRequestValidator> validators;

    @Mock
    TransferRequestCurrencyValidator transferRequestCurrencyValidator;

    @Mock
    TransferRequestPaymentTypeValidator transferRequestPaymentTypeValidator;

    @Mock
    TransferRequestDto transferRequestDto;

    @InjectMocks
    TransferManagerImpl transferManager;


    @Mock
    TransferExecutorInternalService transferExecutorInternalService;

    @Test
    void run_ShouldReturnNotFoundException_WhenExecuteMethodThrowNotFoundException() {
        when(transferExecutorInternalService.execute(transferRequestDto)).thenThrow(NotFoundException.class);
        when(transferRequestDto.getSourcePaymentTypeId()).thenReturn(paymentTypeId);
        when(transferRequestDto.getDestinationPaymentTypeId()).thenReturn(paymentTypeId);

        assertThrows(NotFoundException.class, () -> transferManager.run(transferRequestDto));
    }


    @Test
    void run_ShouldReturnTransferResponseDto_WhenGetExecutorMethodThrowNotFoundException() {
        when(transferRequestDto.getSourcePaymentTypeId()).thenReturn(paymentTypeId);
        when(transferRequestDto.getDestinationPaymentTypeId()).thenReturn(paymentTypeId);
        when(transferManager.getExecutor(transferRequestDto)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> transferManager.run(transferRequestDto));
    }

    @Test
    void getExecutor_ShouldReturnInternalTransferExecutor_WhenSourceAndDestinationPaymentTypeIdIsEquals() {
        when(transferRequestDto.getSourcePaymentTypeId()).thenReturn(paymentTypeId);
        when(transferRequestDto.getDestinationPaymentTypeId()).thenReturn(paymentTypeId);

        TransferExecutor transferExecutor = transferManager.getExecutor(transferRequestDto);

        assertNotNull(transferExecutor);
        assertEquals(transferExecutor, transferExecutorInternalService);
    }

    @Test
    void getExecutor_ShouldThrowRuntimeException_WhenSourceAndDestinationPaymentTypeIdIsNotEquals() {
        when(transferRequestDto.getSourcePaymentTypeId()).thenReturn(paymentTypeId);
        when(transferRequestDto.getDestinationPaymentTypeId()).thenReturn(UUID.randomUUID());

        assertThrows(RuntimeException.class, () -> transferManager.getExecutor(transferRequestDto));
    }

}