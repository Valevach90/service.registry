package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CurrencyDTO;
import com.andersen.banking.meeting_db.entity.Currency;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CurrencyMapper {

    Currency toCurrency(CurrencyDTO currencyDto);
}
