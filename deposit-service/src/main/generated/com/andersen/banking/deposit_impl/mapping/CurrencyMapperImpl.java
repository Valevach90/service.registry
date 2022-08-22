package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.CurrencyDto;
import com.andersen.banking.deposit_db.entities.Currency;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-22T09:11:46+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public CurrencyDto toCurrencyDto(Currency currency) {
        if ( currency == null ) {
            return null;
        }

        CurrencyDto currencyDto = new CurrencyDto();

        currencyDto.setId( currency.getId() );
        currencyDto.setName( currency.getName() );

        return currencyDto;
    }

    @Override
    public Currency toCurrency(CurrencyDto currencyDto) {
        if ( currencyDto == null ) {
            return null;
        }

        Currency currency = new Currency();

        currency.setId( currencyDto.getId() );
        currency.setName( currencyDto.getName() );

        return currency;
    }
}
