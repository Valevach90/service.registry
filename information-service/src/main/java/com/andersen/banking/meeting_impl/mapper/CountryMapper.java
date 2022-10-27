package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_db.entities.Country;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CountryMapper {

    CountryDto country2CountryDto(Country country);

    Country countryDtoToCountry(CountryDto countryDto);

}
