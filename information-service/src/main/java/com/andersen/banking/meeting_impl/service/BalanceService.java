package com.andersen.banking.meeting_impl.service;

import com.andersen.banking.meeting_api.dto.gateway.UserBalance;
import org.springframework.security.core.Authentication;

public interface BalanceService {

    UserBalance getTotalBalance(Authentication authentication);
}
