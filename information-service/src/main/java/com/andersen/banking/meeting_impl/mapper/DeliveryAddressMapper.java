package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.DeliveryAddressDto;
import com.andersen.banking.meeting_db.entities.DeliveryAddress;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeliveryAddressMapper {


/*    @Mapping(source = "country", target = "country.name")
    @Mapping(source = "city", target = "city.name")
    @Mapping(source = "street", target = "street.name")*/
    DeliveryAddress toDeliveryAddress(DeliveryAddressDto addressDto);

/*    @Mapping(source = "country.name", target = "country")
    @Mapping(source = "city.name", target = "city")
    @Mapping(source = "street.name", target = "street")*/
    DeliveryAddressDto toDeliveryAddressDto(DeliveryAddress address);
}
