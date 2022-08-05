package com.andersen.banking.meeting_api.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="deposit-service")
public interface BalanceDepositServiceProxy {

}
