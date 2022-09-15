package com.andersen.banking;

import com.andersen.banking.meeting_api.dto.CreditProductDTO;
import java.util.List;
import java.util.UUID;

public interface CreditProductService {

    CreditProductDTO createCreditProduct(CreditProductDTO creditProductDTO);

    CreditProductDTO getCreditProductById(UUID id);

    List<CreditProductDTO> getListOfCreditProducts();

    CreditProductDTO updateCreditProduct(CreditProductDTO creditProductDTO);

    void deleteCreditProductById(UUID id);
}
