package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.CardProductController;
import com.andersen.banking.meeting_api.dto.CardProductCreateDto;
import com.andersen.banking.meeting_api.dto.CardProductDto;
import com.andersen.banking.meeting_db.entities.CardProduct;
import com.andersen.banking.meeting_impl.mapper.CardProductMapper;
import com.andersen.banking.meeting_impl.service.CardProductService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardProductControllerImpl implements CardProductController {

    private final CardProductService cardProductService;

    private final CardProductMapper cardProductMapper;

    /**
     * End-point to find card product by id
     *
     * @param id
     * @return
     */
    @Override
    public CardProductDto findById(UUID id) {
        log.trace("Receiving card product id: {}", id);

        CardProductDto cardProductDto = cardProductMapper.toCardProductDto(cardProductService.findById(id));

        log.trace("Returning card with id: {}", id);
        return cardProductDto;
    }

    /**
     * End-point to find all card products
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<CardProductDto> findAll(Pageable pageable) {
        log.trace("Receiving request for getting all card products");

        Page<CardProductDto> result = cardProductService.findAll(pageable).map(cardProductMapper::toCardProductDto);

        log.trace("Returning list of card products: {}", result.getContent());
        return result;
    }

    /**
     * End-point to create new card product
     *
     * @param cardProductCreateDto
     * @return
     */
    @Override
    public CardProductDto create(CardProductCreateDto cardProductCreateDto) {
        log.trace("Receiving request for creating card product: {}", cardProductCreateDto);

        CardProduct cardProductToCreate = cardProductMapper.toCardProduct(cardProductCreateDto);

        CardProductDto createdCardProduct = cardProductMapper.toCardProductDto(cardProductService.create(cardProductToCreate));

        log.trace("Returning created card product: {}", createdCardProduct);
        return createdCardProduct;
    }

    /**
     * End-point to
     *
     * @param
     * @return
     */
    @Override
    public CardProductDto update(CardProductDto cardProductDto) {
        log.trace("Receiving request for updating card: {}", cardProductDto);

        CardProduct cardProductToUpdate = cardProductMapper.toCardProduct(cardProductDto);
        CardProductDto updatedCardProduct = cardProductMapper.toCardProductDto(cardProductService.update(cardProductToUpdate));

        log.trace("Returning updated card: {}", updatedCardProduct);
        return updatedCardProduct;
    }

    /**
     * End-point to
     *
     * @param
     * @return
     */
    @Override
    public void deleteById(UUID id) {
        log.trace("Receiving request for deleting card product by id: {}", id);

        cardProductService.deleteById(id);
    }
}
