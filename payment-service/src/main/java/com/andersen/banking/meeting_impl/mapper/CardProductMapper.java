package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CardProductCreateDto;
import com.andersen.banking.meeting_api.dto.CardProductDto;
import com.andersen.banking.meeting_db.entities.CardProduct;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CardProductMapper {

    @Mapping(target = "paymentSystem", source = "typeCard.paymentSystem")
    @Mapping(target = "typeName", source = "typeCard.typeName")
    CardProductDto toCardProductDto(CardProduct cardProduct);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "typeCard.paymentSystem", source = "paymentSystem")
    @Mapping(target = "typeCard.typeName", source ="typeName")
    CardProduct toCardProduct(CardProductCreateDto cardProductCreateDto);

    @Mapping(target = "typeCard.paymentSystem", source = "paymentSystem")
    @Mapping(target = "typeCard.typeName", source ="typeName")
    CardProduct toCardProduct(CardProductDto cardProductDto);
}
