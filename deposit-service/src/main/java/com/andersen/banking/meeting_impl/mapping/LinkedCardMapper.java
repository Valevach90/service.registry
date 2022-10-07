package com.andersen.banking.meeting_impl.mapping;

import com.andersen.banking.meeting_api.dto.LinkedCardDto;
import com.andersen.banking.meeting_db.entities.LinkedCard;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for linked card info.
 */
@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LinkedCardMapper {

    LinkedCardDto toLinkedCardDto(LinkedCard linkedCard);

    LinkedCard toLinkedCard(LinkedCardDto linkedCardDto);
}
