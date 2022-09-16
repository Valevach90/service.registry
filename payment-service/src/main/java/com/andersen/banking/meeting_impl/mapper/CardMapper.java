package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CardCredResponseDto;
import com.andersen.banking.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.meeting_api.dto.CardResponseDto;
import com.andersen.banking.meeting_api.dto.CardUpdateDto;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** This interface presents the basic contract for converting Card to CardDto and vice versa. */
@Mapper(config = MapperConfig.class)
public interface CardMapper {

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "cardProductId", source = "cardProduct.id")
    @Mapping(target = "currency", source = "account.currency")
    @Mapping(target = "balance", source = "account.balance")
    @Mapping(target = "firstTwelveNumbersHash", source = "firstTwelveNumbers")
    CardResponseDto toCardResponseDto(Card card);

    @Mapping(target = "account.id", source = "accountId")
    @Mapping(target = "cardProduct.id", source = "cardProductId")
    Card toCard(CardUpdateDto cardUpdateDto);

    @Mapping(target = "account.id", source = "accountId")
    @Mapping(target = "cardProduct.id", source = "cardProductId")
    @Mapping(target = "cardProduct.typeCard.paymentSystem", ignore = true)
    @Mapping(target = "cardProduct.typeCard.typeName", ignore = true)
    @Mapping(target = "validFromDate", ignore = true)
    @Mapping(target = "expireDate", ignore = true)
    @Mapping(target = "firstTwelveNumbers", ignore = true)
    @Mapping(target = "lastFourNumbers", ignore = true)
    @Mapping(target = "id", ignore = true)
    Card toCard(CardRegistrationDto cardDto);

    @Mapping(target = "firstTwelveNumbersHash", source = "firstTwelveNumbers")
    @Mapping(target = "paymentSystem", source = "cardProduct.typeCard.paymentSystem")
    CardCredResponseDto toCardCredResponseDto(Card card);
}
