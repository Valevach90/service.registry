package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.BankBranchDto;
import com.andersen.banking.meeting_db.entities.BankBranch;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BankBranchMapper {

    @Mapping(source = "address.id", target = "addressId")
    BankBranchDto bankBranch2BankBranchDto(BankBranch bankBranch);

}
