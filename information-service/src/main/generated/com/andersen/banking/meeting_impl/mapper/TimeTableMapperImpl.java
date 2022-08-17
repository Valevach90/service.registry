package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto.TimeTableDtoBuilder;
import com.andersen.banking.meeting_db.entities.BankBranch;
import com.andersen.banking.meeting_db.entities.TimeTable;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T15:16:13+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TimeTableMapperImpl implements TimeTableMapper {

    @Override
    public TimeTableDto timeTable2TimeTableDto(TimeTable timeTable) {
        if ( timeTable == null ) {
            return null;
        }

        TimeTableDtoBuilder timeTableDto = TimeTableDto.builder();

        timeTableDto.branchId( timeTableBankBranchId( timeTable ) );
        timeTableDto.id( timeTable.getId() );
        timeTableDto.dayFrom( timeTable.getDayFrom() );
        timeTableDto.dayTo( timeTable.getDayTo() );
        timeTableDto.timeFrom( timeTable.getTimeFrom() );
        timeTableDto.timeTo( timeTable.getTimeTo() );

        return timeTableDto.build();
    }

    private Long timeTableBankBranchId(TimeTable timeTable) {
        if ( timeTable == null ) {
            return null;
        }
        BankBranch bankBranch = timeTable.getBankBranch();
        if ( bankBranch == null ) {
            return null;
        }
        Long id = bankBranch.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
