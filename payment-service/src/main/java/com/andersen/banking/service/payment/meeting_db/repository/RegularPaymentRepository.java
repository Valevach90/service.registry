package com.andersen.banking.service.payment.meeting_db.repository;

import com.andersen.banking.service.payment.meeting_db.entities.RegularPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository, which works with RegularPayment entity.
 */

@Repository
public interface RegularPaymentRepository extends JpaRepository<RegularPayment, Long> {
}
