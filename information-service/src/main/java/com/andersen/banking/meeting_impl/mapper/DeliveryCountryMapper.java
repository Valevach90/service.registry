package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.DeliveryCountryDto;
import com.andersen.banking.meeting_db.entities.DeliveryCountry;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeliveryCountryMapper {

    DeliveryCountry toDeliveryCountry(DeliveryCountryDto deliveryCountryDto);

    DeliveryCountryDto toDeliveryCountryDto(DeliveryCountry deliveryCountry);
}
