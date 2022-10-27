package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchCreateDto;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchDto;
import com.andersen.banking.meeting_api.dto.bank_brunch.BankBranchResponseDto;
import com.andersen.banking.meeting_db.entities.BankBranch;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BankBranchMapper {

    @Mapping(target = "streetName", source = "street.name")
    @Mapping(target = "cityName", source = "city.name")
    BankBranchResponseDto bankBranch2BankBranchDto(BankBranch bankBranch);

    @Mapping(target = "street.name", source = "streetName")
    @Mapping(target = "city.id", source = "cityId")
    BankBranch toBankBranch(BankBranchCreateDto bankBranchCreateDto);

    @Mapping(target = "street.name", source = "streetName")
    @Mapping(target = "city.id", source = "cityId")
    BankBranch toBankBranch(BankBranchDto bankBranchDto);

}
