package com.andersen.banking.service.registry.meeting_impl.service.processing;

import com.andersen.banking.service.registry.meeting_api.dto.PassportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PassportProcessingService {

    PassportDto findById(Long id);

    PassportDto findByUserId(Long userId);

    PassportDto findByAddressId(Long addressId);

    Page<PassportDto> findAll(Pageable pageable);
}
