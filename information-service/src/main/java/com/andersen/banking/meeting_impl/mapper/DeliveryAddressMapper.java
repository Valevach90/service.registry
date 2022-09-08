package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.DeliveryAddressDto;
import com.andersen.banking.meeting_db.entities.DeliveryAddress;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeliveryAddressMapper {

    DeliveryAddress toDeliveryAddress(DeliveryAddressDto addressDto);

    DeliveryAddressDto toDeliveryAddressDto(DeliveryAddress address);
}
