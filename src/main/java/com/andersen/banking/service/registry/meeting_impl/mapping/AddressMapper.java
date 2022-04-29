package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;
import com.andersen.banking.service.registry.meeting_db.entities.Address;
import com.andersen.banking.service.registry.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper addresses.
 */
@Mapper(config = MapperConfig.class)
public interface AddressMapper {

    @Mapping(target = "userId", source = "user.id")
    AddressDto toAddressDto(Address address);

    List<AddressDto> toAddressDto(List<Address> addresses);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Address toAddressEntity(AddressModifyDto event);
}
