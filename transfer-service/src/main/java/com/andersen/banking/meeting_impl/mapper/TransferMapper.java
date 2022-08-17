package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.message.TransferKafkaDeposit;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferMapper {
    Transfer transferKafkaDeposit2Transfer(TransferKafkaDeposit transferKafkaDeposit);
}
