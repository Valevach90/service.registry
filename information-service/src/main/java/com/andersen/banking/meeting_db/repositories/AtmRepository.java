package com.andersen.banking.meeting_db.repositories;

import com.andersen.banking.meeting_db.entities.Atm;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmRepository extends JpaRepository<Atm, UUID> {

    Page<Atm> findAllByStreetId(Long streetId, Pageable pageable);

    Page<Atm> findAllByBankBranchId(Long bankBranchId, Pageable pageable);
}
