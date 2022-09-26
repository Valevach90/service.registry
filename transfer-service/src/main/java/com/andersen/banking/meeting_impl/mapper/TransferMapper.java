package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.request.TransferRequestDto;
import com.andersen.banking.meeting_api.dto.responce.TransferResponseDto;
import com.andersen.banking.meeting_db.entity.Currency;
import com.andersen.banking.meeting_db.entity.PaymentType;
import com.andersen.banking.meeting_db.entity.Transfer;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import com.andersen.banking.meeting_api.dto.StatusTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferMapper {

    @Mapping(source = "sourcePaymentType.name", target = "sourcePaymentTypeName")
    @Mapping(source = "destinationPaymentType.name", target = "destinationPaymentTypeName")
    @Mapping(source = "currency.name", target = "currencyName")
    @Mapping(source = "createdDate", target = "createTime")
    TransferResponseDto transfer2transferResponseDto(Transfer transfer);

    Transfer transferRequestDto2Transfer(TransferRequestDto transferRequestDto, PaymentType sourcePaymentType, PaymentType destinationPaymentType, Currency currency);
}
