package com.andersen.banking.meeting_api.controller;

import com.andersen.banking.meeting_api.controller.feign.BalanceDepositServiceProxy;
import com.andersen.banking.meeting_api.controller.feign.BalancePaymentServiceProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BalanceControllerImpl implements BalanceController {

    @Autowired
    private BalanceDepositServiceProxy proxyDeposit;

    @Autowired
    private BalancePaymentServiceProxy proxyPayment;

    @Override
    public Long getAllMoneyOwner(Long id) {
        proxyPayment.findByOwnerId(id, Pageable.unpaged());
        return null;
    }
}
