package com.andersen.banking.gateway.meeting_impl.service;

import com.andersen.banking.gateway.meeting_api.dto.gateway.User;

public interface BalanceService {
    User getTotalBalance(Long id);
}
