package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper addresses.
 */
@Mapper(config = MapperConfig.class)
public interface AddressMapper {

    AddressDto toAddressDto(Address address);

    Address toAddress(AddressDto event);
}
