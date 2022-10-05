package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Deposit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DepositRepository extends JpaRepository<Deposit, UUID> {
    Page<Deposit> findDepositByUserId(UUID userId, Pageable pageable);

    Optional<Deposit> findByDepositNumber(String depositNumber);

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("from Deposit d where d.isActive = true AND d.closeDate <= current_date")
    List<Deposit> closingScheduler(Pageable pageable);
}
