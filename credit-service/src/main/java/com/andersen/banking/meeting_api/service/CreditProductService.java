package com.andersen.banking.meeting_api.service;

import com.andersen.banking.meeting_api.dto.CreditProductResponseDTO;
import com.andersen.banking.meeting_api.dto.CreditProductRequestDTO;
import java.util.List;
import java.util.UUID;

public interface CreditProductService {

    CreditProductResponseDTO createCreditProduct(CreditProductRequestDTO creditProductDTO);

    CreditProductResponseDTO getCreditProductById(UUID id);

    List<CreditProductResponseDTO> getListOfCreditProducts();

    CreditProductResponseDTO updateCreditProduct(UUID id, CreditProductRequestDTO creditProductDTO);

    void deleteCreditProductById(UUID id);
}
