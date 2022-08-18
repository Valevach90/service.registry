package com.andersen.banking.service.payment.meeting_impl.mapper;

import com.andersen.banking.service.payment.meeting_api.dto.TypeCardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.TypeCardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.TypeCard;
import com.andersen.banking.service.payment.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypeCardMapper {
    /**
     * This method converts entity TypeCard to TypeCardResponseDto
     *
     * @param typeCard
     * @return
     */
    TypeCardResponseDto typeCard2TypeCardResponseDto(TypeCard typeCard);

    /**
     * This method converts entity TypeCardResponseDto to TypeCard
     *
     * @param typeCardResponseDto
     * @return
     */
    TypeCard typeCardResponseDto2TypeCard(TypeCardResponseDto typeCardResponseDto);

    /**
     * This method converts entity TypeCard to TypeCardUpdateDto
     *
     * @param typeCard
     * @return
     */
    TypeCardUpdateDto typeCard2TypeCardUpdateDto (TypeCard typeCard);

    /**
     * This method converts entity TypeCardUpdateDto to TypeCard
     *
     * @param typeCardUpdateDto
     * @return
     */
    TypeCard typeCardUpdateDto2TypeCard(TypeCardUpdateDto typeCardUpdateDto);
}
