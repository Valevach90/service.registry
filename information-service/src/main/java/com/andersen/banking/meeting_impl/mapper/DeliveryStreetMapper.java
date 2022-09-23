package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.DeliveryStreetDto;
import com.andersen.banking.meeting_db.entities.DeliveryStreet;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeliveryStreetMapper {

    DeliveryStreet toDeliveryStreet(DeliveryStreetDto deliveryStreetDto);

    DeliveryStreetDto toDeliveryStreetDto(DeliveryStreet deliveryStreet);
}
