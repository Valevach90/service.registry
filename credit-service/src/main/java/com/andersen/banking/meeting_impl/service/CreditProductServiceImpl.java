package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_impl.exception.CreditProductNotFoundException;
import com.andersen.banking.meeting_api.dto.CreditProductDTO;
import com.andersen.banking.meeting_db.repository.CreditProductRepository;
import com.andersen.banking.meeting_impl.mapper.CreditProductMapper;
import com.andersen.banking.meeting_api.service.CreditProductService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditProductServiceImpl implements CreditProductService {

    private final CreditProductRepository creditProductRepository;
    private final CreditProductMapper creditProductMapper;

    @Override
    public CreditProductDTO createCreditProduct(CreditProductDTO creditProductDTO) {
        //ToDo такой продукт уже есть
        //ToDo логирование
        CreditProduct creditProduct = creditProductMapper.toCreditProduct(creditProductDTO);

        CreditProduct savedCreditProduct = creditProductRepository.save(creditProduct);

        return creditProductMapper.toCreditProductDTO(savedCreditProduct);
    }

    @Transactional(readOnly = true)
    @Override
    public CreditProductDTO getCreditProductById(UUID id) {

        Optional<CreditProduct> creditProductOptional = creditProductRepository.findById(id);

        if (creditProductOptional.isEmpty()) {
            throw new CreditProductNotFoundException(id);
        } else {
            return creditProductMapper.toCreditProductDTO(creditProductOptional.get());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CreditProductDTO> getListOfCreditProducts() {

        List<CreditProduct> creditProductList = creditProductRepository.findAll();

        return creditProductMapper.toCreditProductDTOList(creditProductList);
    }

    @Override
    public CreditProductDTO updateCreditProduct(CreditProductDTO creditProductDTO) {

        CreditProduct creditProduct = creditProductMapper.toCreditProduct(creditProductDTO);

        Optional<CreditProduct> creditProductOptional = creditProductRepository.
            findById(creditProduct.getUuid());

        if (creditProductOptional.isEmpty()) {
            throw new CreditProductNotFoundException(creditProduct.getUuid());
        } else {
            CreditProduct creditProductReturned = creditProductRepository.save(
                creditProductOptional.get());
            return creditProductMapper.toCreditProductDTO(creditProductReturned);
        }
    }

    @Override
    public void deleteCreditProductById(UUID id) {

        Optional<CreditProduct> creditProductOptional = creditProductRepository.findById(id);
        if (creditProductOptional.isEmpty()) {
            throw new CreditProductNotFoundException(id);
        } else {
            creditProductRepository.deleteById(id);
        }
    }
}
