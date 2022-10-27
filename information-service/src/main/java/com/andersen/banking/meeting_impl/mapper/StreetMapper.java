package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.StreetDto;
import com.andersen.banking.meeting_db.entities.Street;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StreetMapper {

    @Mapping(source = "city.id", target = "cityId")
    StreetDto street2StreetDto(Street street);


    Street streetDtoToStreet(StreetDto streetDto);

}
