package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import com.andersen.banking.service.registry.meeting_impl.config.MeetingMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for passport.
 */
@Mapper(config = MeetingMapperConfig.class)
public interface PassportMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "addressId", source = "address.id")
    PassportDto toPassportDto(PassportEntity passportEntity);
}
