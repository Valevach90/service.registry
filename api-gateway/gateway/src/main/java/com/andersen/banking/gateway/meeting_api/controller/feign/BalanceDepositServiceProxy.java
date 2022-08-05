package com.andersen.banking.gateway.meeting_api.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(name="deposit-service")
@Component
public interface BalanceDepositServiceProxy {

}
