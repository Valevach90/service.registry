package com.andersen.banking.deposit.service.deposit_impl.service.impl;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit.service.deposit_db.repositories.DepositProductRepository;
import com.andersen.banking.deposit.service.deposit_impl.exceptions.NotFoundException;
import com.andersen.banking.deposit.service.deposit_impl.service.DepositProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositProductServiceImpl implements DepositProductService {

    private final DepositProductRepository depositProductRepository;

    @Override
    @Transactional
    public DepositProduct create(DepositProduct product) {
        log.info("Creating deposit product: {}", product);

        product.setId(null);

        DepositProduct savedProduct = depositProductRepository.save(product);

        log.info("Created deposit product: {}", savedProduct);
        return savedProduct;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepositProduct> findById(Long id) {
        log.info("Find deposit product by id: {}", id);

        Optional<DepositProduct> product = depositProductRepository.findById(id);

        log.info("Found deposit product by id: {}", product);
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DepositProduct> findAll(Pageable pageable) {
        log.info("Find all deposit products for pageable: {}", pageable);

        Page<DepositProduct> pageOfProducts = depositProductRepository.findAll(pageable);

        log.info("Found {} deposit products", pageOfProducts.getContent().size());
        return pageOfProducts;
    }

    @Override
    @Transactional
    public void update(DepositProduct product) {
        log.info("Updating deposit product: {}", product);

        DepositProduct foundProduct = depositProductRepository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException(DepositProduct.class, product.getId()));

        depositProductRepository.save(product);
        log.info("Deposit product: {} updated to version: {}", foundProduct, product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting deposit product with id: {}", id);

        DepositProduct foundProduct = depositProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(DepositProduct.class, id));

        depositProductRepository.deleteById(id);
        log.info("Deleted deposit product: {}", foundProduct);
    }
}