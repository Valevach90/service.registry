package com.andersen.banking.gateway.meeting_api.controller;

import com.andersen.banking.gateway.meeting_api.controller.feign.BalancePaymentService;
import com.andersen.banking.gateway.meeting_api.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BalanceControllerImpl implements BalanceController {

    @Autowired
    private BalancePaymentService proxyPayment;

    @Override
    public Double getAllMoneyOwner(Long id) {
        Page<AccountDto> byOwnerId = proxyPayment.findByOwnerId(id, Pageable.unpaged());
        double sum = byOwnerId.getContent().stream().mapToDouble(AccountDto::getBalance).sum();
        log.info("sum is {}", sum);
        return sum;
    }
}
