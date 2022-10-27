package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.TimeTableCreateDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_db.entities.TimeTable;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TimeTableMapper {

    @Mapping(source = "bankBranch.id", target = "branchId")
    TimeTableDto timeTable2TimeTableDto(TimeTable timeTable);

    @Mapping(target = "bankBranch", ignore = true)
    TimeTable toTimeTable(TimeTableCreateDto timeTableCreateDto);

    @Mapping(target = "bankBranch", ignore = true)
    TimeTable toTimeTable(TimeTableDto timeTableDto);
}
