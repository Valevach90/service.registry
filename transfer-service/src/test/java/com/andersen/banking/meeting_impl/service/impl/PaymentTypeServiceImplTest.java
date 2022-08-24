package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_db.entity.PaymentType;
import com.andersen.banking.meeting_db.repository.PaymentTypeRepository;
import com.andersen.banking.meeting_impl.mapper.PaymentTypeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentTypeServiceImplTest {

    private final static UUID ID = UUID.randomUUID();

    @Mock
    PaymentTypeRepository paymentTypeRepository;
    @Mock
    PaymentTypeMapper paymentTypeMapper;
    @Mock
    List<PaymentType> paymentTypes;
    @Mock
    List<PaymentTypeResponseDto> paymentTypeResponseDtos;
    @InjectMocks
    PaymentTypeServiceImpl paymentTypeService;

    @Test
    void getAllPaymentTypes_ShouldReturnListOfPaymentTypeResonseDto_WhenListOfPaymentTypeYIsNotEmpty() {
        final var paymentTypeResponseDto = mock(PaymentTypeResponseDto.class);
        final var paymentType = mock(PaymentType.class);
        paymentTypes = List.of(paymentType, paymentType, paymentType);
        paymentTypeResponseDtos = List.of(paymentTypeResponseDto, paymentTypeResponseDto, paymentTypeResponseDto);
        when(paymentTypeRepository.findAll()).thenReturn(paymentTypes);
        when(paymentTypeMapper.paymentType2PaymentTypeResponseDto(paymentType)).thenReturn(paymentTypeResponseDto);

        final List<PaymentTypeResponseDto> actual = paymentTypeService.getAllPaymentTypes();

        assertNotNull(actual);
        assertEquals(paymentTypeResponseDtos, actual);
    }


    @Test
    void getAllCurrencies_ShouldReturnEmptyListOfCurrencyResponseDto_WhenListOfCurrencyIsEmpty() {
        paymentTypes = List.of();
        paymentTypeResponseDtos = List.of();
        when(paymentTypeRepository.findAll()).thenReturn(paymentTypes);

        final List<PaymentTypeResponseDto> actual = paymentTypeService.getAllPaymentTypes();

        assertNotNull(actual);
        assertEquals(paymentTypeResponseDtos, actual);
    }

    @Test
    void getCurrencyById_ShouldReturnCurrency_WhenRepositoryFindByIdReturnCurrency() {
        final var paymentType = mock(PaymentType.class);
        when(paymentTypeRepository.findById(ID)).thenReturn(Optional.ofNullable(paymentType));

        PaymentType actual = paymentTypeService.getPaymentTypeById(ID);

        assertNotNull(actual);
        assertEquals(paymentType, actual);
    }

    @Test
    void getCurrencyById_ShouldThrowNotFoundException_WhenRepositoryFindByIdReturnCurrency() {
        final var paymentType = mock(PaymentType.class);
        when(paymentTypeRepository.findById(ID)).thenReturn(Optional.ofNullable(paymentType));

        PaymentType actual = paymentTypeService.getPaymentTypeById(ID);

        assertNotNull(actual);
        assertEquals(paymentType, actual);
    }
}