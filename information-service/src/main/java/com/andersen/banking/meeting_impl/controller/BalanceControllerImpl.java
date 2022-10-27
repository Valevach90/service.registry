package com.andersen.banking.meeting_impl.controller;

import com.andersen.banking.meeting_api.controller.BalanceController;
import com.andersen.banking.meeting_api.dto.gateway.UserBalance;
import com.andersen.banking.meeting_impl.service.impl.BalanceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BalanceControllerImpl implements BalanceController {

    private final BalanceServiceImpl balanceService;

    public BalanceControllerImpl(BalanceServiceImpl balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public UserBalance getAllMoneyOwner(Authentication authentication) {
        log.info("Get balance for user with auth!");
        return balanceService.getTotalBalance(authentication);
    }
}
