package com.andersen.banking.service.registry.meeting_impl.service.local;

import com.andersen.banking.service.registry.meeting_db.entities.PassportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PassportService {

    PassportEntity findById(Long id);

    PassportEntity findByUserId(Long userId);

    PassportEntity findByAddressId(Long addressId);

    Page<PassportEntity> findAll(Pageable pageable);
}
