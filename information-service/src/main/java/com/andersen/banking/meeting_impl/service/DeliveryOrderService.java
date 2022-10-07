package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.DeliveryOrderCreateRequestDto;
import com.andersen.banking.meeting_api.dto.DeliveryOrderDto;
import com.andersen.banking.meeting_api.dto.DeliveryTypeDto;
import com.andersen.banking.meeting_db.entities.DeliveryOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Service for working with delivery orders.
 */

public interface DeliveryOrderService {

    /**
     * Create new delivery order.
     *
     * @param order delivery order to create
     * @return delivery order
     */
    DeliveryOrderDto create(DeliveryOrderCreateRequestDto order);

    /**
     * Get delivery order by id.
     *
     * @param id delivery order id
     * @return delivery order
     */
    DeliveryOrderDto findById(UUID id);

    /**
     * Find all delivery orders.
     *
     * @param pageable page object
     * @return page of delivery orders
     */
    Page<DeliveryOrderDto> findAll(Pageable pageable);

    /**
     * Find all delivery types.
     *
     * @param pageable page object
     * @return page of delivery types
     */
    Page<DeliveryTypeDto> findAllDeliveryTypes(Pageable pageable);

    /**
     * Get delivery orders for user.
     *
     * @param userId user id
     * @return page of delivery order
     */
    Page<DeliveryOrderDto> findDeliveryOrderByUserId(UUID userId, Pageable pageable);

    /**
     * Get delivery order for card.
     *
     * @param cardId card id
     * @return delivery order
     */
    DeliveryOrderDto findDeliveryOrderByCardId(UUID cardId);

    /**
     * Update delivery order.
     *
     * @param deliveryOrderDto delivery order to update
     */
    void update(DeliveryOrderDto deliveryOrderDto);
}
