package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CardProductCreateDto;
import com.andersen.banking.meeting_api.dto.CardProductDto;
import com.andersen.banking.meeting_db.entities.CardProduct;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CardProductMapper {

    CardProductDto toCardProductDto(CardProduct cardProduct);

    @Mapping(target = "id", ignore = true)
    CardProduct toCardProduct(CardProductCreateDto cardProductCreateDto);

    CardProduct toCardProduct(CardProductDto cardProductDto);
}
