package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.AddressDto;
import com.andersen.banking.meeting_api.dto.AddressDto.AddressDtoBuilder;
import com.andersen.banking.meeting_db.entities.Address;
import com.andersen.banking.meeting_db.entities.Street;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-25T12:24:39+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto address2AddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDtoBuilder addressDto = AddressDto.builder();

        addressDto.streetId( addressStreetId( address ) );
        addressDto.id( address.getId() );
        addressDto.house( address.getHouse() );
        addressDto.building( address.getBuilding() );

        return addressDto.build();
    }

    private Long addressStreetId(Address address) {
        if ( address == null ) {
            return null;
        }
        Street street = address.getStreet();
        if ( street == null ) {
            return null;
        }
        Long id = street.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}