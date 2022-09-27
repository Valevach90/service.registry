package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.CreditProductRequestDTO;
import com.andersen.banking.meeting_api.dto.CreditProductResponseDTO;
import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_impl.config.MapperConfig;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CreditProductMapper {

    CreditProduct toCreditProduct(CreditProductRequestDTO creditProductDTO);

    CreditProductResponseDTO toCreditProductDTO(CreditProduct creditProduct);

    List<CreditProductResponseDTO> toCreditProductDTOList(List<CreditProduct> creditProductList);

    void updateCreditProduct(CreditProductRequestDTO creditProductDTO
        , @MappingTarget CreditProduct product);
}
