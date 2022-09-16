package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.CreditProductController;
import com.andersen.banking.meeting_api.service.CreditProductService;
import com.andersen.banking.meeting_api.dto.CreditProductDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CreditProductControllerImpl implements CreditProductController {

    private final CreditProductService creditProductService;

    @Override
    public CreditProductDTO create(CreditProductDTO creditProductDTO) {

        log.info("Creating credit product: " + creditProductDTO);

        return creditProductService.createCreditProduct(creditProductDTO);
    }

    @Override
    public CreditProductDTO getById(UUID id) {

        log.info("Find credit product by id: " + id);

        return creditProductService.getCreditProductById(id);
    }

    @Override
    public List<CreditProductDTO> getAll() {

        log.info("Find all credit product");

        return creditProductService.getListOfCreditProducts();
    }

    @Override
    public void update(CreditProductDTO creditProductDTO) {

        log.info("Change credit product: " + creditProductDTO);

        creditProductService.updateCreditProduct(creditProductDTO);
    }

    @Override
    public void deleteById(UUID id) {

        log.info("Delete credit product is deleted");

        creditProductService.deleteCreditProductById(id);
    }
}
