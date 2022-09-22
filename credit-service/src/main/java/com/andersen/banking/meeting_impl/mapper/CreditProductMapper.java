package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CreditProductResponseDTO;
import com.andersen.banking.meeting_api.dto.CreditProductRequestDTO;
import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CreditProductMapper {

    CreditProduct toCreditProduct(CreditProductRequestDTO creditProductDTO); //делает нулевку

    CreditProductResponseDTO toCreditProductDTO(CreditProduct creditProduct);

    List<CreditProductResponseDTO> toCreditProductDTOList(List<CreditProduct> creditProductList);
}
