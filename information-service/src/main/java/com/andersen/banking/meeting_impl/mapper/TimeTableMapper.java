package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_db.entities.TimeTable;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TimeTableMapper {

    TimeTableDto timeTable2TimeTableDto(TimeTable timeTable);

}
