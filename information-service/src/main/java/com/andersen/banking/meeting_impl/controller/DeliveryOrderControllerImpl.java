package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.DeliveryOrderController;
import com.andersen.banking.meeting_api.dto.DeliveryOrderCreateRequestDto;
import com.andersen.banking.meeting_api.dto.DeliveryOrderDto;
import com.andersen.banking.meeting_api.dto.DeliveryTypeDto;
import com.andersen.banking.meeting_impl.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Delivery order controller implementation.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeliveryOrderControllerImpl implements DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    @Override
    public DeliveryOrderDto create(DeliveryOrderCreateRequestDto deliveryOrderDto) {
        log.trace("Receiving request for creating delivery order: {}", deliveryOrderDto);

        DeliveryOrderDto createdOrder = deliveryOrderService.create(deliveryOrderDto);

        log.trace("Returning created order: {}", createdOrder);
        return createdOrder;
    }

    @Override
    public DeliveryOrderDto findById(UUID id) {
        log.trace("Find delivery order by id: {}", id);

        DeliveryOrderDto deliveryOrder = deliveryOrderService.findById(id);

        log.trace("Found delivery order by id: {}", deliveryOrder);
        return deliveryOrder;
    }

    @Override
    public Page<DeliveryOrderDto> findAll(Pageable pageable) {
        log.trace("Find all delivery orders for pageable: {}", pageable);

        Page<DeliveryOrderDto> allDeliveryOrders = deliveryOrderService.findAll(pageable);

        log.trace("Found {} delivery orders", allDeliveryOrders.getContent().size());
        return allDeliveryOrders;
    }

    @Override
    public Page<DeliveryTypeDto> findAllDeliveryTypes(Pageable pageable) {
        log.trace("Find all delivery types for pageable: {}", pageable);

        Page<DeliveryTypeDto> allDeliveryTypes = deliveryOrderService.findAllDeliveryTypes(pageable);

        log.trace("Found delivery orders", allDeliveryTypes.getContent());
        return allDeliveryTypes;
    }

    @Override
    public Page<DeliveryOrderDto> findDeliveryOrderByUserId(UUID userId, Pageable pageable) {
        log.trace("Find delivery orders by user id: {}", userId);

        Page<DeliveryOrderDto> deliveryOrders = deliveryOrderService.findDeliveryOrderByUserId(userId, pageable);

        log.trace("Found {} delivery orders for user id {}", deliveryOrders.getContent().size(), userId);
        return deliveryOrders;
    }

    @Override
    public DeliveryOrderDto findDeliveryOrderByCardId(UUID cardId) {
        log.trace("Find delivery order by card id: {}", cardId);

        DeliveryOrderDto deliveryOrder = deliveryOrderService.findDeliveryOrderByCardId(cardId);

        log.trace("Found delivery order by card id: {}", deliveryOrder);
        return deliveryOrder;
    }

    @Override
    public void update(DeliveryOrderDto deliveryOrderDto) {
        log.trace("Updating delivery order: {}", deliveryOrderDto);

        deliveryOrderService.update(deliveryOrderDto);

        log.trace("Delivery order is updated");
    }
}
