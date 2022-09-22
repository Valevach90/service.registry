package com.andersen.banking.meeting_api.service;

import com.andersen.banking.meeting_api.dto.CreditProductDTO;
import java.util.List;
import java.util.UUID;

public interface CreditProductService {

    CreditProductDTO createCreditProduct(CreditProductDTO creditProductDTO);

    CreditProductDTO getCreditProductById(UUID id);

    List<CreditProductDTO> getListOfCreditProducts();

    CreditProductDTO updateCreditProduct(UUID id, CreditProductDTO creditProductDTO);

    void deleteCreditProductById(UUID id);
}
