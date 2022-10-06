package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entities.RegularPayment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** JpaRepository, which works with RegularPayment entity. */
public interface RegularPaymentRepository extends JpaRepository<RegularPayment, UUID> {

    @Query(value = "SELECT * FROM regular_payment "
            + "WHERE next_date <= CURRENT_DATE "
            + "for update skip locked "
            + "LIMIT 10", nativeQuery = true)
    List<RegularPayment> findRegularPaymentsToExecute();
}
