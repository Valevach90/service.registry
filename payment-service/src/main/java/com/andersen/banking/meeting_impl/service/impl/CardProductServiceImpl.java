package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.CardProduct;
import com.andersen.banking.meeting_db.repository.CardProductRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.CardProductService;
import com.andersen.banking.meeting_impl.service.TypeCardService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@CacheConfig(cacheNames = "cardProductCache")
public class CardProductServiceImpl implements CardProductService {

    private final CardProductRepository cardProductRepository;

    private final TypeCardService typeCardService;

    @Override
    @Transactional
    @CachePut(key = "#cardProduct.id")
    public CardProduct create(CardProduct cardProduct) {
        log.info("creating card product: {}", cardProduct);

        cardProduct.setTypeCard(typeCardService
                .findByPaymentSystemAndTypeName(cardProduct.getTypeCard().getPaymentSystem(), cardProduct.getTypeCard().getTypeName()));

        CardProduct savedCardProduct = cardProductRepository.save(cardProduct);

        log.info("Created card product: {}", savedCardProduct);
        return savedCardProduct;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<CardProduct> findAll(Pageable pageable) {
        log.info("Find all card products for pageable: {}", pageable);

        Page<CardProduct> cardProducts = cardProductRepository.findAll(pageable);

        log.info("Found {} card products", cardProducts.getSize());
        return cardProducts;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    public CardProduct findById(UUID id) {
        log.info("Trying to find card product with id: {}", id);

        CardProduct cardProduct = cardProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CardProduct.class, id));

        log.info("Card product with id: {} successfully found", id);
        return cardProduct;
    }

    @Override
    @Transactional
    @CachePut(key = "#updateCardProduct.id")
    public CardProduct update(CardProduct updateCardProduct) {
        log.info("Trying to update card product: {}", updateCardProduct);

        findById(updateCardProduct.getId());
        updateCardProduct.setTypeCard(typeCardService
                .findByPaymentSystemAndTypeName(updateCardProduct.getTypeCard().getPaymentSystem(), updateCardProduct.getTypeCard().getTypeName()));

        CardProduct updatedCardProduct = cardProductRepository.save(updateCardProduct);
        log.info("Card product was updated: {}", updatedCardProduct);
        return updatedCardProduct;
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public void deleteById(UUID id) {
        log.info("Trying to delete card product with id: {}", id);

        CardProduct cardProductToDelete = findById(id);

        cardProductRepository.delete(cardProductToDelete);
        log.info("Deleted card product: {}", cardProductToDelete);
    }
}
