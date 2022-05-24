package com.andersen.banking.service.payment.meeting_impl.config;

import com.andersen.banking.service.payment.meeting_impl.exceptions.MapperException;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.MapperConfig(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    unexpectedValueMappingException = MapperException.class
)
public interface MapperConfig {

}
