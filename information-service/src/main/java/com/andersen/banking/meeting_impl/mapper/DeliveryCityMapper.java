package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.DeliveryCityDto;
import com.andersen.banking.meeting_db.entities.DeliveryCity;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeliveryCityMapper {

    DeliveryCity toDeliveryCity(DeliveryCityDto deliveryCityDto);

    DeliveryCityDto toDeliveryCityDto(DeliveryCityMapper deliveryCity);
}
