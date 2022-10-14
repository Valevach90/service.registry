package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.AtmDto;
import com.andersen.banking.meeting_db.entities.Atm;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AtmMapper {

    AtmDto toAtmDto(Atm atm);

    Atm toAtm(AtmDto atmDto);

}
