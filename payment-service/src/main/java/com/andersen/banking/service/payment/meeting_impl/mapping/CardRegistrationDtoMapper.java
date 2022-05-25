package com.andersen.banking.service.payment.meeting_impl.mapping;

import com.andersen.banking.service.payment.meeting_api.dto.CardRegistrationDto;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * This interface presents the basic contract for converting CardRegistrationDto to Card.
 */
@Mapper(config = MapperConfig.class)
public interface CardRegistrationDtoMapper {

  @Mapping(target = "account.id", source = "accountId")
  @Mapping(target = "id", ignore = true)
  Card toCard(CardRegistrationDto cardDto);
}
