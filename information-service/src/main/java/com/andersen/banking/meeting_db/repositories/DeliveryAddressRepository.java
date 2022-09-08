package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.DeliveryAddress;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, UUID> {
}
