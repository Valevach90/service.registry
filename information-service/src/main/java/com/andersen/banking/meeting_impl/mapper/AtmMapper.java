package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.AtmDtoRequest;
import com.andersen.banking.meeting_api.dto.AtmDtoResponse;
import com.andersen.banking.meeting_db.entities.Atm;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AtmMapper {

    @Mapping(target = "bankBranch.id", source = "bankBranch")
    @Mapping(target = "street.id", source = "streetId")
    @Mapping(target = "currency.id", source = "currencyId")
    Atm toAtm(AtmDtoRequest atmDtoRequest);

    AtmDtoResponse toAtmDtoResponse(Atm atm);

}
