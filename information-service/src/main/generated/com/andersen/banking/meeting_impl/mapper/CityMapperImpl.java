package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_api.dto.CityDto.CityDtoBuilder;
import com.andersen.banking.meeting_db.entities.City;
import com.andersen.banking.meeting_db.entities.Country;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-22T17:07:12+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public CityDto city2CityDto(City city) {
        if ( city == null ) {
            return null;
        }

        CityDtoBuilder cityDto = CityDto.builder();

        cityDto.countryId( cityCountryId( city ) );
        cityDto.id( city.getId() );
        cityDto.name( city.getName() );

        return cityDto.build();
    }

    private Long cityCountryId(City city) {
        if ( city == null ) {
            return null;
        }
        Country country = city.getCountry();
        if ( country == null ) {
            return null;
        }
        Long id = country.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
