package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CountryDto;
import com.andersen.banking.meeting_api.dto.CountryDto.CountryDtoBuilder;
import com.andersen.banking.meeting_db.entities.Country;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-06T02:30:38+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public CountryDto country2CountryDto(Country country) {
        if ( country == null ) {
            return null;
        }

        CountryDtoBuilder countryDto = CountryDto.builder();

        countryDto.id( country.getId() );
        countryDto.name( country.getName() );

        return countryDto.build();
    }
}
