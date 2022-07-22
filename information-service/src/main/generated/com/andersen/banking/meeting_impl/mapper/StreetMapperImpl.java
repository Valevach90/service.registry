package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.StreetDto;
import com.andersen.banking.meeting_api.dto.StreetDto.StreetDtoBuilder;
import com.andersen.banking.meeting_db.entities.City;
import com.andersen.banking.meeting_db.entities.Street;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-22T17:07:12+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class StreetMapperImpl implements StreetMapper {

    @Override
    public StreetDto street2StreetDto(Street street) {
        if ( street == null ) {
            return null;
        }

        StreetDtoBuilder streetDto = StreetDto.builder();

        streetDto.cityId( streetCityId( street ) );
        streetDto.id( street.getId() );
        streetDto.name( street.getName() );

        return streetDto.build();
    }

    private Long streetCityId(Street street) {
        if ( street == null ) {
            return null;
        }
        City city = street.getCity();
        if ( city == null ) {
            return null;
        }
        Long id = city.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
