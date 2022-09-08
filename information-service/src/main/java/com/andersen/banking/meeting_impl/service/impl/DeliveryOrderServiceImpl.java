package com.andersen.banking.meeting_impl.service.impl;


import com.andersen.banking.meeting_api.dto.DeliveryOrderCreateRequestDto;
import com.andersen.banking.meeting_api.dto.DeliveryOrderDto;
import com.andersen.banking.meeting_api.dto.DeliveryTypeDto;
import com.andersen.banking.meeting_db.entities.DeliveryOrder;
import com.andersen.banking.meeting_db.repositories.DeliveryAddressRepository;
import com.andersen.banking.meeting_db.repositories.DeliveryOrderRepository;
import com.andersen.banking.meeting_db.repositories.DeliveryTypeRepository;
import com.andersen.banking.meeting_impl.exception.NotFoundException;
import com.andersen.banking.meeting_impl.mapper.DeliveryOrderMapper;
import com.andersen.banking.meeting_impl.mapper.DeliveryTypeMapper;
import com.andersen.banking.meeting_impl.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

/**
 * Delivery order service implementation.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;

    private final DeliveryTypeRepository deliveryTypeRepository;

    private final DeliveryAddressRepository deliveryAddressRepository;

    private final DeliveryOrderMapper deliveryOrderMapper;

    private final DeliveryTypeMapper deliveryTypeMapper;

    @Override
    public DeliveryOrderDto create(DeliveryOrderCreateRequestDto orderCreateRequest) {
        log.info("Creating delivery order: {}", orderCreateRequest);

        DeliveryOrder newOrder = deliveryOrderMapper.toDeliveryOrder(orderCreateRequest);

        newOrder.setOpeningTime(new Timestamp(System.currentTimeMillis()));
        newOrder.setIsDelivered(false);

        deliveryAddressRepository.save(newOrder.getDeliveryAddress());
        DeliveryOrder savedOrder = deliveryOrderRepository.save(newOrder);

        log.info("Created delivery order: {}", savedOrder);
        return deliveryOrderMapper.toDeliveryOrderDto(savedOrder);
    }

    @Override
    public DeliveryOrderDto findById(UUID id) {
        log.info("Find delivery order by id: {}", id);

        Optional<DeliveryOrder> deliveryOrder = deliveryOrderRepository.findById(id);

        DeliveryOrderDto deliveryOrderDto = deliveryOrderMapper.toDeliveryOrderDto(deliveryOrder.orElseThrow(
                () -> new NotFoundException(DeliveryOrder.class, "order id", id)));

        log.info("Found delivery order: {}", deliveryOrderDto);
        return deliveryOrderDto;
    }

    @Override
    public Page<DeliveryOrderDto> findAll(Pageable pageable) {
        log.info("Find all delivery orders for pageable: {}", pageable);

        Page<DeliveryOrderDto> allDeliveryOrders = deliveryOrderRepository.findAll(pageable)
                .map(deliveryOrderMapper::toDeliveryOrderDto);

        log.info("Found {} delivery orders", allDeliveryOrders.getContent().size());
        return allDeliveryOrders;
    }

    @Override
    public Page<DeliveryTypeDto> findAllDeliveryTypes(Pageable pageable) {
        log.info("Find all delivery types for pageable: {}", pageable);

        Page<DeliveryTypeDto> allDeliveryTypes = deliveryTypeRepository.findAll(pageable)
                .map(deliveryTypeMapper::toDeliveryTypeDto);

        log.info("Found delivery types: {}", allDeliveryTypes.getContent());
        return allDeliveryTypes;
    }

    @Override
    public Page<DeliveryOrderDto> findDeliveryOrderByUserId(UUID userId, Pageable pageable) {
        log.info("Find delivery orders by user id: {}", userId);

        Page<DeliveryOrderDto> deliveryOrders = deliveryOrderRepository.findByUserId(userId, pageable)
                .map(deliveryOrderMapper::toDeliveryOrderDto);

        log.info("Found {} delivery orders for user id {}", deliveryOrders.getContent().size(), userId);
        return deliveryOrders;
    }

    @Override
    public DeliveryOrderDto findDeliveryOrderByCardId(UUID cardId) {
        log.info("Find delivery order by card id: {}", cardId);

        Optional<DeliveryOrder> deliveryOrder = deliveryOrderRepository.findByCardId(cardId);

        DeliveryOrderDto deliveryOrderDto = deliveryOrderMapper.toDeliveryOrderDto(deliveryOrder.orElseThrow(
                () -> new NotFoundException(DeliveryOrder.class, "card id", cardId)));

        log.info("Found delivery order: {}", deliveryOrderDto);
        return deliveryOrderDto;
    }

    @Override
    public void update(DeliveryOrderDto deliveryOrderDto) {
        log.info("Updating delivery order: {}", deliveryOrderDto);

        DeliveryOrder deliveryOrder = deliveryOrderMapper.toDeliveryOrder(deliveryOrderDto);

        deliveryOrderRepository.save(deliveryOrder);

        log.info("Delivery order: {} updated to version: {}", deliveryOrderDto, deliveryOrder);
    }
}
