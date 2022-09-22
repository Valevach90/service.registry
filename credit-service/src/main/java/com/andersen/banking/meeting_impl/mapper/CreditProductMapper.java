package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CreditProductDTO;
import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CreditProductMapper {

    CreditProduct toCreditProduct(CreditProductDTO creditProductDTO);

    CreditProductDTO toCreditProductDTO(CreditProduct creditProduct);

    List<CreditProductDTO> toCreditProductDTOList(List<CreditProduct> creditProductList);
}
