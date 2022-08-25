package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.kafka.config.KafkaProperties;
import com.andersen.banking.meeting_impl.kafka.message.RequestKafkaTransferMessage;
import com.andersen.banking.meeting_impl.service.TransferMoneyTopicSender;
import com.andersen.banking.meeting_impl.service.TransferService;
import com.andersen.banking.meeting_impl.util.Converter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferExecutorInternalServiceTest {

    private final static String CARD = "CARD";
    private final static String DEPOSIT = "DEPOSIT";
    private final static UUID ID = UUID.randomUUID();
    private final static String PAYMENT_REQUEST_TOPIC_NAME = "transfer_to_payment_request";
    private final static String DEPOSIT_REQUEST_TOPIC_NAME = "transfer_to_deposit_request";

    @Mock
    KafkaProperties kafkaProperties;
    @Mock
    TransferService transferService;
    @Mock
    TransferMoneyTopicSender transferMoneyTopicSender;
    @Mock
    Converter<RequestKafkaTransferMessage, Transfer> converter;
    @InjectMocks
    TransferExecutorInternalService transferExecutorInternalService;


    @Test
    void execute_shouldThrowRuntimeException_WhenTransferServiceCreateMethodThrowRuntimeException() {
        final TransferRequestDto transferRequestDto = mock(TransferRequestDto.class);
        when(transferService.create(transferRequestDto)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> transferExecutorInternalService.execute(transferRequestDto));
        verify(transferService).create(transferRequestDto);
    }

    @Test
    void execute_shouldThrowNotFoundException_WhenTransferServiceFindByIdMethodThrowNotFoundException() {
        final Transfer transfer = mock(Transfer.class);
        final RequestKafkaTransferMessage requestKafkaTransferMessage = mock(RequestKafkaTransferMessage.class);
        final TransferRequestDto transferRequestDto = mock(TransferRequestDto.class);
        when(transferService.create(transferRequestDto)).thenReturn(transfer);
        when(transfer.getId()).thenReturn(ID);
        when(converter.convert(transfer)).thenReturn(requestKafkaTransferMessage);
        when(requestKafkaTransferMessage.getSourceType()).thenReturn(CARD);
        when(requestKafkaTransferMessage.getDestinationType()).thenReturn(CARD);
        when(transferExecutorInternalService.getTopicNameByPaymentTypes(CARD, CARD)).thenReturn(PAYMENT_REQUEST_TOPIC_NAME);
        doNothing().when(transferMoneyTopicSender).sendRequestMessage(PAYMENT_REQUEST_TOPIC_NAME, requestKafkaTransferMessage);
        when(transferService.findById(ID)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> transferExecutorInternalService.execute(transferRequestDto));
        verify(transferService).findById(transfer.getId());
    }


    @Test
    void execute_shouldReturnTransferResponseDto_WhenTransferIsCreated() {
        final Transfer transfer = mock(Transfer.class);
        final RequestKafkaTransferMessage requestKafkaTransferMessage = mock(RequestKafkaTransferMessage.class);
        final TransferRequestDto transferRequestDto = mock(TransferRequestDto.class);
        final TransferResponseDto transferResponseDto = mock(TransferResponseDto.class);
        when(transferService.create(transferRequestDto)).thenReturn(transfer);
        when(transferService.findById(ID)).thenReturn(transferResponseDto);
        when(transfer.getId()).thenReturn(ID);
        when(converter.convert(transfer)).thenReturn(requestKafkaTransferMessage);
        when(requestKafkaTransferMessage.getSourceType()).thenReturn(CARD);
        when(requestKafkaTransferMessage.getDestinationType()).thenReturn(CARD);
        when(transferExecutorInternalService.getTopicNameByPaymentTypes(CARD, CARD)).thenReturn(PAYMENT_REQUEST_TOPIC_NAME);
        doNothing().when(transferMoneyTopicSender).sendRequestMessage(PAYMENT_REQUEST_TOPIC_NAME, requestKafkaTransferMessage);

        final TransferResponseDto actual = transferExecutorInternalService.execute(transferRequestDto);

        assertNotNull(actual);
        assertEquals(transferResponseDto, actual);
        verify(transferService).findById(transfer.getId());
    }

    @Test
    void getTopicNameByPaymentTypes_shouldReturnPaymentRequestTopic_WhenSourceAndDestinationPaymentTypesEqualsCard() {
        when(kafkaProperties.getPayment_transfer_request_topic_name()).thenReturn(PAYMENT_REQUEST_TOPIC_NAME);

        final String actualName = transferExecutorInternalService.getTopicNameByPaymentTypes(CARD, CARD);

        assertNotNull(actualName);
        assertEquals(PAYMENT_REQUEST_TOPIC_NAME, actualName);
    }

    @Test
    void getTopicNameByPaymentTypes_shouldReturnDepositRequestTopic_WhenSourceAndDestinationPaymentTypesEqualsDeposit() {
        when(kafkaProperties.getDeposit_transfer_request_topic_name()).thenReturn(DEPOSIT_REQUEST_TOPIC_NAME);

        final String actualName = transferExecutorInternalService.getTopicNameByPaymentTypes(DEPOSIT, DEPOSIT);

        assertNotNull(actualName);
        assertEquals(DEPOSIT_REQUEST_TOPIC_NAME, actualName);
    }


    @Test
    void getTopicNameByPaymentTypes_shouldThrowRuntimeException_WhenSourceAndDestinationPaymentTypesIsNotEquals() {

        assertThrows(RuntimeException.class, () -> transferExecutorInternalService.getTopicNameByPaymentTypes(CARD, DEPOSIT));
    }


}