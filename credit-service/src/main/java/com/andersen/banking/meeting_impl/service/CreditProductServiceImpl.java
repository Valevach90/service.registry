package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.CreditProductDTO;
import com.andersen.banking.meeting_api.service.CreditProductService;
import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_db.repository.CreditProductRepository;
import com.andersen.banking.meeting_impl.exception.CreditProductAlreadyExistException;
import com.andersen.banking.meeting_impl.exception.CreditProductNotFoundException;
import com.andersen.banking.meeting_impl.mapper.CreditProductMapper;
import java.util.List;
import java.util.Optional;
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

    @Override
    public CreditProductDTO createCreditProduct(CreditProductDTO creditProductDTO) {

        log.info("Creating credit product: {}", creditProductDTO);

        CreditProduct creditProduct = creditProductMapper.toCreditProduct(creditProductDTO);

        if (creditProductRepository.findById(creditProduct.getUuid()).isPresent()) {
            throw new CreditProductAlreadyExistException(creditProduct.getUuid());
        }

        CreditProductDTO creditProductDTOReturned = creditProductMapper.toCreditProductDTO(
            creditProductRepository.save(creditProduct));

        log.info("Created credit product: {}", creditProductDTOReturned);

        return creditProductDTOReturned;
    }

    @Transactional(readOnly = true)
    @Override
    public CreditProductDTO getCreditProductById(UUID id) {

        log.info("Find credit product by id: {}", id);

        Optional<CreditProduct> creditProductOptional = creditProductRepository.findById(id);

        if (creditProductOptional.isEmpty()) {
            throw new CreditProductNotFoundException(id);
        } else {
            log.info("Credit product with id {} successfully found", id);

            return creditProductMapper.toCreditProductDTO(creditProductOptional.get());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CreditProductDTO> getListOfCreditProducts() {

        log.info("Getting credit products");

        List<CreditProduct> creditProductList = creditProductRepository.findAll();

        log.info("Found {} credit products", creditProductList.size());

        return creditProductMapper.toCreditProductDTOList(creditProductList);
    }

    @Override
    public CreditProductDTO updateCreditProduct(CreditProductDTO creditProductDTO) {

        CreditProduct creditProduct = creditProductMapper.toCreditProduct(creditProductDTO);

        Optional<CreditProduct> creditProductOptional = creditProductRepository.
            findById(creditProduct.getUuid());

        log.info("Updating credit product to: {}", creditProductOptional);

        if (creditProductOptional.isEmpty()) {
            throw new CreditProductNotFoundException(creditProduct.getUuid());
        }
        if (creditProductRepository.findAll().stream().anyMatch(
            product -> product.equals(creditProduct))) {
            throw new CreditProductAlreadyExistException(creditProduct.getUuid());
        } else {
            setAttributes(creditProductOptional, creditProduct);

            CreditProduct creditProductReturned = creditProductRepository.save(
                creditProductOptional.get());

            log.info("Credit product: {} updated to version: {}", creditProductOptional,
                creditProductReturned);

            return creditProductMapper.toCreditProductDTO(creditProductReturned);
        }
    }

    @Override
    public void deleteCreditProductById(UUID id) {

        log.info("Deleting credit product with id: {}", id);

        Optional<CreditProduct> creditProductOptional = creditProductRepository.findById(id);
        if (creditProductOptional.isEmpty()) {
            throw new CreditProductNotFoundException(id);
        } else {
            creditProductRepository.deleteById(id);

            log.info("Deleted credit product: {}", creditProductOptional.get());
        }
    }

    private void setAttributes(Optional<CreditProduct> creditProductOptional,
        CreditProduct creditProduct) {
        if (creditProductOptional.isPresent()) {
            creditProductOptional.get().setName(creditProduct.getName());
            creditProductOptional.get().setMinSum(creditProduct.getMinSum());
            creditProductOptional.get().setMaxSum(creditProduct.getMaxSum());
            creditProductOptional.get().setCurrency(creditProduct.getCurrency());
            creditProductOptional.get().setMinLoanRate(creditProduct.getMinLoanRate());
            creditProductOptional.get().setMaxLoanRate(creditProduct.getMaxLoanRate());
            creditProductOptional.get().setNeedGuarantee(creditProduct.getNeedGuarantee());
            creditProductOptional.get().setEarlyRepayment(creditProduct.getEarlyRepayment());
            creditProductOptional.get().setMinTerm(creditProduct.getMinTerm());
            creditProductOptional.get().setMaxTerm(creditProduct.getMaxTerm());
            creditProductOptional.get().setDescription(creditProduct.getDescription());
            creditProductOptional.get().setCalculationMode(creditProduct.getCalculationMode());
            creditProductOptional.get().setGracePeriodMonth(creditProduct.getGracePeriodMonth());
            creditProductOptional.get()
                .setNeedIncomeStatement(creditProduct.getNeedIncomeStatement());
        }
    }
}
