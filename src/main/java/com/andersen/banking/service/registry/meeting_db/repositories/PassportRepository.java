package com.andersen.banking.service.registry.meeting_db.repositories;

import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<PassportEntity, Long> {

    Optional<PassportEntity> findByUserId(Long userId);

    Optional<PassportEntity> findByAddressId(Long addressId);

    @NonNull
    Page<PassportEntity> findAll(@NonNull Pageable pageable);
}
