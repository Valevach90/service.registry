package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.AddressDto;
import com.andersen.banking.meeting_db.entities.Address;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AddressMapper {

    @Mapping(source = "street.id", target = "streetId")
    AddressDto address2AddressDto(Address address);

}
