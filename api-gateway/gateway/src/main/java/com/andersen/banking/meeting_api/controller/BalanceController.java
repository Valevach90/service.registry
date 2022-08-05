package com.andersen.banking.meeting_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Balance controller", description = "endpoints to get balance owner in gateway")
@RequestMapping(value = "/api/v1/balance")
public interface BalanceController {
    @Operation(summary = "Get sum money", description = "get total balance from deposits and accounts")
    @GetMapping("/{id}")
    Long getAllMoneyOwner(@PathVariable Long id);
}
