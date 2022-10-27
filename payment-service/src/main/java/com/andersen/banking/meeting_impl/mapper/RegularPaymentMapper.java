package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.RegularPaymentRequestDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentResponseDto;
import com.andersen.banking.meeting_api.dto.RegularPaymentUpdateDto;
import com.andersen.banking.meeting_db.entities.RegularPayment;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegularPaymentMapper {

    @Mapping(source = "sourceCardId", target = "sourceCard.id")
    @Mapping(source = "recipientCardId", target = "recipientCard.id")
    @Mapping(target = "nextDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegularPayment toRegularPayment(RegularPaymentRequestDto regularPaymentRequestDto);

    @Mapping(source = "sourceCardId", target = "sourceCard.id")
    @Mapping(source = "recipientCardId", target = "recipientCard.id")
    RegularPayment toRegularPayment(RegularPaymentUpdateDto regularPaymentUpdateDto);

    @Mapping(source = "sourceCard.id", target = "sourceCardId")
    @Mapping(source = "recipientCard.id", target = "recipientCardId")
    RegularPaymentResponseDto toRegularPaymentResponseDto(RegularPayment regularPayment);
}
