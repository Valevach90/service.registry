package com.andersen.banking.deposit_impl.mapping;

import com.andersen.banking.deposit_api.dto.CurrencyDto;
import com.andersen.banking.deposit_db.entities.Currency;
import com.andersen.banking.deposit_impl.config.MapperConfig;
import org.mapstruct.Mapper;

/**
 * Mapper for deposit currency.
 */
@Mapper(config = MapperConfig.class)
public interface CurrencyMapper {

    CurrencyDto toCurrencyDto(Currency currency);

    Currency toCurrency(CurrencyDto currencyDto);
}
