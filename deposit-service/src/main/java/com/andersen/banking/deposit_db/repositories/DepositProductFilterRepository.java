package com.andersen.banking.deposit_db.repositories;

import com.andersen.banking.deposit_api.dto.DepositProductFilterDto;
import com.andersen.banking.deposit_db.entities.DepositProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepositProductFilterRepository {

    DepositProductFilterDto getDepositProductAvailableSettings() throws IllegalAccessException;

    Page<DepositProduct> getDepositProductsByFilter(DepositProductFilterDto depositProductFilterDto, Pageable pageable) throws IllegalAccessException;
}