package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_db.entity.Currency;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_impl.mapper.CurrencyMapper;
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
class CurrencyServiceImplTest {

    private final static UUID ID = UUID.randomUUID();

    @Mock
    CurrencyRepository currencyRepository;
    @Mock
    CurrencyMapper currencyMapper;
    @Mock
    List<Currency> currencies;
    @Mock
    List<CurrencyResponseDto> currencyResponseDtos;
    @InjectMocks
    CurrencyServiceImpl currencyService;

    @Test
    void getAllCurrencies_ShouldReturnListOfCurrencyResonseDto_WhenListOfCurrencYIsNotEmpty() {
        final var currencyResponseDto = mock(CurrencyResponseDto.class);
        final var currency = mock(Currency.class);
        currencies = List.of(currency, currency, currency);
        currencyResponseDtos = List.of(currencyResponseDto, currencyResponseDto, currencyResponseDto);
        when(currencyRepository.findAll()).thenReturn(currencies);
        when(currencyMapper.currency2CurrencyResponseDto(currency)).thenReturn(currencyResponseDto);

        final List<CurrencyResponseDto> actual = currencyService.getAllCurrencies();

        assertNotNull(actual);
        assertEquals(currencyResponseDtos, actual);
    }


    @Test
    void getAllCurrencies_ShouldReturnEmptyListOfCurrencyResponseDto_WhenListOfCurrencyIsEmpty() {
        currencies = List.of();
        currencyResponseDtos = List.of();
        when(currencyRepository.findAll()).thenReturn(currencies);

        final List<CurrencyResponseDto> actual = currencyService.getAllCurrencies();

        assertNotNull(actual);
        assertEquals(currencyResponseDtos, actual);
    }

    @Test
    void getCurrencyById_ShouldReturnCurrency_WhenRepositoryFindByIdReturnCurrency() {
        final var currency = mock(Currency.class);
        when(currencyRepository.findById(ID)).thenReturn(Optional.ofNullable(currency));

        Currency actual = currencyService.getCurrencyById(ID);

        assertNotNull(actual);
        assertEquals(currency, actual);
    }

    @Test
    void getCurrencyById_ShouldThrowNotFoundException_WhenRepositoryFindByIdReturnCurrency() {
        final var currency = mock(Currency.class);
        when(currencyRepository.findById(ID)).thenReturn(Optional.ofNullable(currency));

        Currency actual = currencyService.getCurrencyById(ID);

        assertNotNull(actual);
        assertEquals(currency, actual);
    }
}