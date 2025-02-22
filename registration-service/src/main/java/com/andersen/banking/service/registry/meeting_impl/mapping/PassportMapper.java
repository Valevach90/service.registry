package com.andersen.banking.service.registry.meeting_impl.mapping;

import com.andersen.banking.service.registry.meeting_api.dto.PassportCreateDto;
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

    @Mapping(target = "addressId", source = "address.id")
    PassportDto toPassportDto(Passport passport);

    @Mapping(target = "address.id", source = "addressId")
    Passport toPassport(PassportDto passportDto);

    @Mapping(target = "address.id", source = "addressId")
    @Mapping(target = "id", ignore = true)
    Passport toPassport(PassportCreateDto passportDto);
}
