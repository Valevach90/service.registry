package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, UUID> {

    Optional<DeliveryType> findByTypeName(String typeName);
}
