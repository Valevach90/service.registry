package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.responce.TransferStatusResponseDto;
import com.andersen.banking.meeting_db.entity.TransferStatus;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferStatusMapper {

    TransferStatusResponseDto transferStatus2TransferStatusResponseDto(TransferStatus transferStatus);

    TransferStatus TransferStatusResponseDto2transferStatus(TransferStatusResponseDto transferStatusResponseDto);
}
