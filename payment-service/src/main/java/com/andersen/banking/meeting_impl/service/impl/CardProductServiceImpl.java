package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.CardProduct;
import com.andersen.banking.meeting_db.repository.CardProductRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.service.CardProductService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CardProductServiceImpl implements CardProductService {

    private final CardProductRepository cardProductRepository;


    @Override
    @Transactional
    public CardProduct create(CardProduct cardProduct) {
        log.info("creating card product: {}", cardProduct);



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
    public CardProduct findById(UUID id) {
        log.info("Trying to find card product with id: {}", id);

        CardProduct cardProduct = cardProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CardProduct.class, id));

        log.info("Card product with id: {} successfully found", id);
        return cardProduct;
    }

    @Override
    @Transactional
    public CardProduct update(CardProduct updateCardProduct) {
        log.info("Trying to update card product: {}", updateCardProduct);

        findById(updateCardProduct.getId());

        CardProduct updatedCardProduct = cardProductRepository.save(updateCardProduct);
        log.info("Card product was updated: {}", updatedCardProduct);
        return updatedCardProduct;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        log.info("Trying to delete card product with id: {}", id);

        CardProduct cardProductToDelete = findById(id);

        cardProductRepository.delete(cardProductToDelete);
        log.info("Deleted card product: {}", cardProductToDelete);
    }
}
