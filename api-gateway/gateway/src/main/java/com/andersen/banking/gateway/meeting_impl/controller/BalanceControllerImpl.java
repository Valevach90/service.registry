package com.andersen.banking.gateway.meeting_impl.controller;

import com.andersen.banking.gateway.meeting_api.controller.BalanceController;
import com.andersen.banking.gateway.meeting_api.dto.gateway.User;
import com.andersen.banking.gateway.meeting_impl.service.impl.BalanceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BalanceControllerImpl implements BalanceController {

    @Autowired
    BalanceServiceImpl balanceService;

    @Override
    public User getAllMoneyOwner(Long id) {
        return balanceService.getTotalBalance(id);
    }
}
