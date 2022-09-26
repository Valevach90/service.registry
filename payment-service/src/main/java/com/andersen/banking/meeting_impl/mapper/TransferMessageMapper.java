package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_impl.config.MapperConfig;
import com.andersen.banking.meeting_impl.kafka.message.RequestTransferMessage;
import com.andersen.banking.meeting_impl.kafka.message.ResponseTransferMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * This interface presents the basic contract for converting Account to AccountDto and vice versa.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferMessageMapper {

    @Mapping(target = "statusDescription", ignore = true)
    ResponseTransferMessage requestToResponse(RequestTransferMessage requestTransferMessage);
}
