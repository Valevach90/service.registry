package com.andersen.banking.service.payment.meeting_impl.mapper;

import com.andersen.banking.service.payment.meeting_api.dto.CardDto;
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
  CardDto toCardDto(Card card);

  @Mapping(target = "account.id", source = "accountId")
  Card toCard(CardDto cardDto);
}
