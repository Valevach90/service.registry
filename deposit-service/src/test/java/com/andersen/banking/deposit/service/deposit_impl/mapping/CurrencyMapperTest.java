package com.andersen.banking.deposit.service.deposit_impl.mapping;

import com.andersen.banking.deposit.service.deposit_api.dto.CurrencyDto;
import com.andersen.banking.deposit.service.deposit_api.dto.DepositTypeDto;
import com.andersen.banking.deposit.service.deposit_db.entities.Currency;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.andersen.banking.deposit.service.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;
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