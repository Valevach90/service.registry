package com.andersen.banking.meeting_impl.service.impl;

import com.andersen.banking.meeting_db.entities.DepositType;
import com.andersen.banking.meeting_db.repositories.DepositTypeRepository;
import com.andersen.banking.meeting_impl.service.DepositTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositTypeServiceImpl implements DepositTypeService {

    private final DepositTypeRepository depositTypeRepository;

    @Override
    public List<DepositType> findAll() {
        log.debug("Get list of deposit type.");
        return depositTypeRepository.findAll();
    }
}
