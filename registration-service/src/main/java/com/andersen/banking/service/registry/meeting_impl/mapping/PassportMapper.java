package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import com.andersen.banking.service.registry.meeting_db.entities.Passport;
import com.andersen.banking.service.registry.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for passport.
 */
@Mapper(config = MapperConfig.class)
public interface PassportMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "addressId", source = "address.id")
    PassportDto toPassportDto(Passport passport);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "address.id", source = "addressId")
    Passport toPassport(PassportDto passportDto);
}
