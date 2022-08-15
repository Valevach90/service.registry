package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferMapper {

    TransferResponseDto transfer2transferResponseDto(Transfer transfer);
}
