package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_db.entities.CardProduct;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardProductService {

    /**
     * This method registers new CardProduct.
     *
     * @param cardProduct
     * @return CardProduct
     */
    CardProduct create(CardProduct cardProduct);

    /**
     * This method returns page of CardProduct entities.
     *
     * @param pageable
     * @return
     */
    Page<CardProduct> findAll(Pageable pageable);

    /**
     * Return CardProduct by id.
     *
     * @param id of the cardProduct
     * @return CardProduct
     */
    CardProduct findById(UUID id);

    /**
     * Update cardProducts.
     *
     * @param updateCardProduct update account
     * @return CardProduct
     */
    CardProduct update(CardProduct updateCardProduct);

    /**
     * This method deletes the CardProduct with the given id.
     *
     * @param id
     * @return CardProduct
     */
    void deleteById(UUID id);
}
