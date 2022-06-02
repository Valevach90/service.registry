package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_db.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-02T11:49:08+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto toAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setUserId( addressUserId( address ) );
        addressDto.setId( address.getId() );
        addressDto.setZipCode( address.getZipCode() );
        addressDto.setCountry( address.getCountry() );
        addressDto.setRegion( address.getRegion() );
        addressDto.setLocation( address.getLocation() );
        addressDto.setCity( address.getCity() );
        addressDto.setStreet( address.getStreet() );
        addressDto.setHouse( address.getHouse() );
        addressDto.setBuilding( address.getBuilding() );
        addressDto.setFlat( address.getFlat() );

        return addressDto;
    }

    @Override
    public Address toAddress(AddressDto event) {
        if ( event == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( event.getId() );
        address.setZipCode( event.getZipCode() );
        address.setCountry( event.getCountry() );
        address.setRegion( event.getRegion() );
        address.setLocation( event.getLocation() );
        address.setCity( event.getCity() );
        address.setStreet( event.getStreet() );
        address.setHouse( event.getHouse() );
        address.setBuilding( event.getBuilding() );
        address.setFlat( event.getFlat() );

        return address;
    }

    private Long addressUserId(Address address) {
        if ( address == null ) {
            return null;
        }
        User user = address.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
