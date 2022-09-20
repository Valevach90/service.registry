package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.CurrencyDto;
import com.andersen.banking.meeting_db.entities.Currency;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper for deposit currency.
 */
@Mapper(config = MapperConfig.class)
public interface CurrencyMapper {

    CurrencyDto toCurrencyDto(Currency currency);

    Currency toCurrency(CurrencyDto currencyDto);
}
