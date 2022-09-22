package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.CreditProductDTO;
import com.andersen.banking.meeting_api.service.CreditProductService;
import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_db.repository.CreditProductRepository;
import com.andersen.banking.meeting_impl.exception.CreditProductAlreadyExistException;
import com.andersen.banking.meeting_impl.exception.CreditProductNotFoundException;
import com.andersen.banking.meeting_impl.mapper.CreditProductMapper;
import com.andersen.banking.meeting_impl.mapper.CurrencyMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CreditProductServiceImpl implements CreditProductService {

    private final CreditProductRepository creditProductRepository;
    private final CreditProductMapper creditProductMapper;
    private final CurrencyMapper currencyMapper;

    @Override
    public CreditProductDTO createCreditProduct(CreditProductDTO productDTO) {

        log.info("Creating credit product: {}", productDTO);

        var creditProductEntity = creditProductMapper.toCreditProduct(productDTO);

        var creditProduct = creditProductRepository.save(creditProductEntity);

        var creditProductDTO = creditProductMapper.toCreditProductDTO(creditProduct);

        log.info("Created credit product: {}", creditProductDTO);

        return creditProductDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public CreditProductDTO getCreditProductById(UUID id) {

        log.info("Find credit product by id: {}", id);

        var creditProduct = getById(id);
        var creditProductDto = creditProductMapper.toCreditProductDTO(creditProduct);

        log.info("Credit product with id {} successfully found", id);

        return creditProductDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditProductDTO> getListOfCreditProducts() {

        log.info("Getting credit products");

        List<CreditProduct> creditProductList = creditProductRepository.findAll();

        log.info("Found {} credit products", creditProductList.size());

        return creditProductMapper.toCreditProductDTOList(creditProductList);
    }

    @Override
    public CreditProductDTO updateCreditProduct(UUID id, CreditProductDTO productDTO) {

        log.info("Updating credit product to: {}", productDTO);

        var creditProduct = getById(id);

        setAttributes(creditProduct, productDTO);

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

    private void assertIfExistCreditProduct(UUID id) {
        var creditProduct = creditProductRepository.findById(id);
        if (creditProduct.isPresent()) {
            throw new CreditProductAlreadyExistException(id);
        }
    }

    private CreditProduct getById(UUID id) {
        return creditProductRepository.findById(id).
            orElseThrow(() -> new CreditProductNotFoundException(id));
    }

    private void setAttributes(CreditProduct product, CreditProductDTO creditProductDTO) {
        product.setName(creditProductDTO.getName());
        product.setMinSum(creditProductDTO.getMinSum());
        product.setMaxSum(creditProductDTO.getMaxSum());
        product.setCurrency(currencyMapper.toCurrency(creditProductDTO.getCurrency()));
        product.setMinLoanRate(creditProductDTO.getMinLoanRate());
        product.setMaxLoanRate(creditProductDTO.getMaxLoanRate());
        product.setNeedGuarantee(creditProductDTO.getNeedGuarantee());
        product.setEarlyRepayment(creditProductDTO.getEarlyRepayment());
        product.setMinTerm(creditProductDTO.getMinTerm());
        product.setMaxTerm(creditProductDTO.getMaxTerm());
        product.setDescription(creditProductDTO.getDescription());
        product.setCalculationMode(creditProductDTO.getCalculationMode());
        product.setGracePeriodMonth(creditProductDTO.getGracePeriodMonth());
        product.setNeedIncomeStatement(creditProductDTO.getNeedIncomeStatement());
    }
}
