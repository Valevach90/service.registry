package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.DeliveryOrderCreateRequestDto;
import com.andersen.banking.meeting_api.dto.DeliveryOrderDto;
import com.andersen.banking.meeting_db.entities.DeliveryOrder;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeliveryOrderMapper {

    DeliveryOrder toDeliveryOrder(DeliveryOrderCreateRequestDto deliveryOrderCreateRequestDto);

    DeliveryOrder toDeliveryOrder(DeliveryOrderDto deliveryOrderDto);

    DeliveryOrderDto toDeliveryOrderDto(DeliveryOrder deliveryOrder);
}
