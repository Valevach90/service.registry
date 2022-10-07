package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.DeliveryTypeDto;
import com.andersen.banking.meeting_db.entities.DeliveryType;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeliveryTypeMapper {

    DeliveryType toDeliveryType(DeliveryTypeDto typeDto);

    DeliveryTypeDto toDeliveryTypeDto(DeliveryType type);
}
