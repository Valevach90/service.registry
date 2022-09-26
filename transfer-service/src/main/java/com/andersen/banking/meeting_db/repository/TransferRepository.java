package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.Transfer;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {

    Page<Transfer> findByUserId(UUID id, Pageable pageable);

    boolean existsByIdAndStatus(UUID id, int status);
}
