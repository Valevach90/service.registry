package com.andersen.banking.service.payment.meeting_impl.mapper;

import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.CardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * This interface presents the basic contract for converting Card to CardDto and vice versa.
 */
@Mapper(config = MapperConfig.class)
public interface CardMapper {

    @Mapping(target = "accountId", source = "account.id")
    CardResponseDto toCardResponseDto(Card card);

    @Mapping(target = "account.id", source = "accountId")
    Card toCard(CardUpdateDto cardUpdateDto);

    @Mapping(target = "account.id", source = "accountId")
    @Mapping(target = "id", ignore = true)
    Card toCard(CardRegistrationDto cardDto);
}