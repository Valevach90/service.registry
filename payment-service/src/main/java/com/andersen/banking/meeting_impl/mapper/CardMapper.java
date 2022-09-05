package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CardCredResponseDto;
import com.andersen.banking.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.meeting_api.dto.CardResponseDto;
import com.andersen.banking.meeting_api.dto.CardUpdateDto;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * This interface presents the basic contract for converting Card to CardDto and vice versa.
 */
@Mapper(config = MapperConfig.class)
public interface CardMapper {

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "paymentSystem", source = "typeCard.paymentSystem")
    @Mapping(target = "typeName", source = "typeCard.typeName")
    @Mapping(target = "currency", source = "account.currency")
    @Mapping(target = "balance", source = "account.balance")
    @Mapping(target = "firstTwelveNumbersHash", source = "firstTwelveNumbers")
    CardResponseDto toCardResponseDto(Card card);

    @Mapping(target = "account.id", source = "accountId")
    @Mapping(target = "typeCard.paymentSystem", source = "paymentSystem")
    @Mapping(target = "typeCard.typeName", source = "typeName")
    Card toCard(CardUpdateDto cardUpdateDto);

    @Mapping(target = "account.id", source = "accountId")
    @Mapping(target = "typeCard.paymentSystem", source = "paymentSystem")
    @Mapping(target = "typeCard.typeName", source = "typeName")
    @Mapping(target = "id", ignore = true)
    Card toCard(CardRegistrationDto cardDto);

    @Mapping(target = "firstTwelveNumbersHash", source = "firstTwelveNumbers")
    @Mapping(target = "paymentSystem", source = "typeCard.paymentSystem")
    CardCredResponseDto toCardCredResponseDto(Card card);

}
