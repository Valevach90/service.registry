package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
    PaymentType findByName(String name);
}
