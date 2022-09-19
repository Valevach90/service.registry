package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.CurrencyDto;
import com.andersen.banking.meeting_db.entities.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateCurrency;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateCurrencyDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CurrencyMapperImpl.class)
public class CurrencyMapperTest {

    private Currency currency;
    private CurrencyDto currencyDto;

    @Autowired
    CurrencyMapper currencyMapper;

    @BeforeEach
    void initialize(){
        currency = generateCurrency();
        currencyDto = generateCurrencyDto(currency);
    }

    @Test
    void toCurrency_whenOk_shouldReturnCurrency(){
        Currency result = currencyMapper.toCurrency(currencyDto);

        assertEquals(currency, result);
    }

    @Test
    void toCurrencyDto_whenOk_shouldReturnCurrencyDto(){
        CurrencyDto result = currencyMapper.toCurrencyDto(currency);

        assertEquals(currencyDto, result);
    }
}
