package com.andersen.banking.gateway.meeting_api.controller.feign;

import reactivefeign.spring.config.ReactiveFeignClient;

@ReactiveFeignClient(name="deposit-service")
public interface BalanceDepositService {

}
