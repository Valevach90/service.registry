package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.responce.CurrencyResponseDto;
import com.andersen.banking.meeting_db.entity.Currency;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T02:25:33+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public CurrencyResponseDto currency2CurrencyResponseDto(Currency currency) {
        if ( currency == null ) {
            return null;
        }

        CurrencyResponseDto currencyResponseDto = new CurrencyResponseDto();

        currencyResponseDto.setId( currency.getId() );
        currencyResponseDto.setName( currency.getName() );
        currencyResponseDto.setDescription( currency.getDescription() );

        return currencyResponseDto;
    }
}
