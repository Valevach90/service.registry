package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.DeliveryOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, UUID> {

    Page<DeliveryOrder> findByUserId(UUID uuid, Pageable pageable);

    Optional<DeliveryOrder> findByCardId(UUID uuid);
}
