package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.TimeTableDto;
import com.andersen.banking.meeting_api.dto.TimeTableDto.TimeTableDtoBuilder;
import com.andersen.banking.meeting_db.entities.Address;
import com.andersen.banking.meeting_db.entities.TimeTable;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-25T12:24:39+0300",
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

        timeTableDto.addressId( timeTableAddressId( timeTable ) );
        timeTableDto.id( timeTable.getId() );
        timeTableDto.dayFrom( timeTable.getDayFrom() );
        timeTableDto.dayTo( timeTable.getDayTo() );
        timeTableDto.timeFrom( timeTable.getTimeFrom() );
        timeTableDto.timeTo( timeTable.getTimeTo() );

        return timeTableDto.build();
    }

    private Long timeTableAddressId(TimeTable timeTable) {
        if ( timeTable == null ) {
            return null;
        }
        Address address = timeTable.getAddress();
        if ( address == null ) {
            return null;
        }
        Long id = address.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
