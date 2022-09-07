package com.andersen.banking.meeting_db.repository;

import com.andersen.banking.meeting_db.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {

    Optional<Transfer> findById(UUID id);

    Page<Transfer> findByUserId(UUID id, Pageable pageable);

}
