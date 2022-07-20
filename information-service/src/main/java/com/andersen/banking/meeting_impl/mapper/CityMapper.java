package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CityDto;
import com.andersen.banking.meeting_db.entities.City;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CityMapper {


    @Mapping(source = "country.id", target = "countryId")
    CityDto city2CityDto(City city);

}
