package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.CreditProductRequestDTO;
import com.andersen.banking.meeting_api.dto.CreditProductResponseDTO;
import com.andersen.banking.meeting_api.service.CreditProductService;
import com.andersen.banking.meeting_api.service.CurrencyService;
import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_db.repository.CreditProductRepository;
import com.andersen.banking.meeting_impl.exception.CreditProductNotFoundException;
import com.andersen.banking.meeting_impl.mapper.CreditProductMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditProductServiceImpl implements CreditProductService {

    private final CreditProductRepository creditProductRepository;
    private final CreditProductMapper creditProductMapper;
    private final CurrencyService currencyService;

    @Override
    public CreditProductResponseDTO createCreditProduct(CreditProductRequestDTO productDTO) {

        log.info("Creating credit product: {}", productDTO);

        var creditProductEntity = creditProductMapper.toCreditProduct(productDTO);

        setCurrency(productDTO.getCurrencyId(),creditProductEntity);
        var creditProduct = creditProductRepository.save(creditProductEntity);

        var creditProductDTO = creditProductMapper.toCreditProductDTO(creditProduct);

        log.info("Created credit product: {}", creditProductDTO);

        return creditProductDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public CreditProductResponseDTO getCreditProductById(UUID id) {

        log.info("Find credit product by id: {}", id);

        var creditProduct = getById(id);
        var creditProductDto = creditProductMapper.toCreditProductDTO(creditProduct);

        log.info("Credit product with id {} successfully found", id);

        return creditProductDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditProductResponseDTO> getListOfCreditProducts() {

        log.info("Getting credit products");

        List<CreditProduct> creditProductList = creditProductRepository.findAll();

        log.info("Found {} credit products", creditProductList.size());

        return creditProductMapper.toCreditProductDTOList(creditProductList);
    }

    @Override
    public CreditProductResponseDTO updateCreditProduct(UUID id,
        CreditProductRequestDTO productDTO) {

        log.info("Updating credit product to: {}", productDTO);

        var creditProduct = getById(id);

        creditProductMapper.updateCreditProduct(productDTO, creditProduct);
        setCurrency(productDTO.getCurrencyId(),creditProduct);
        var creditProductDTO = creditProductMapper.toCreditProductDTO(
            creditProductRepository.save(creditProduct)
        );

        log.info("Credit product: {} updated to version: {}", creditProduct,
            creditProductDTO);

        return creditProductDTO;
    }

    @Override
    public void deleteCreditProductById(UUID id) {

        log.info("Deleting credit product with id: {}", id);

        var creditProduct = getById(id);

        creditProductRepository.delete(creditProduct);

        log.info("Deleted credit product: {}", creditProduct);
    }

    private CreditProduct getById(UUID id) {
        return creditProductRepository.findById(id).
            orElseThrow(() -> new CreditProductNotFoundException(id));
    }

    private void setCurrency(UUID currencyId,CreditProduct creditProduct){
        var currency = currencyService.getCurrencyById(currencyId);
        creditProduct.setCurrency(currency);
    }
}
