package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entities.RegularPayment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository, which works with RegularPayment entity.
 */

public interface RegularPaymentRepository extends JpaRepository<RegularPayment, Long> {
}
