package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.responce.PaymentTypeResponseDto;
import com.andersen.banking.meeting_db.entity.PaymentType;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentTypeMapper {
    PaymentTypeResponseDto paymentType2PaymentTypeResponseDto(PaymentType paymentType);

}

