package com.andersen.banking;

import com.andersen.banking.meeting_api.CreditProductDTO;
import com.andersen.banking.meeting_db.CreditProduct;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditProductMapper {

    CreditProduct toCreditProduct(CreditProductDTO creditProductDTO);

    CreditProductDTO toCreditProductDTO(CreditProduct creditProduct);

    List<CreditProductDTO> toCreditProductDTOList(List<CreditProduct> creditProductList);
}
