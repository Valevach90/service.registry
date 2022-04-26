package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.AddressDto;
import com.andersen.banking.service.registry.meeting_api.dto.AddressModifyDto;
import com.andersen.banking.service.registry.meeting_db.entities.AddressEntity;
import com.andersen.banking.service.registry.meeting_impl.config.MeetingMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper addresses.
 */
@Mapper(config = MeetingMapperConfig.class)
public interface AddressMapper {

    @Mapping(target = "userId", source = "user.id")
    AddressDto toAddressDto(AddressEntity address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    AddressEntity toAddressEntity(AddressModifyDto event);

    void updateAddressDetails(@MappingTarget AddressEntity oldAddress, AddressEntity updateEvent);
}
