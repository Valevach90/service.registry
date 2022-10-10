package com.andersen.banking.meeting_impl.feign;

import com.andersen.banking.meeting_impl.feign.dto.CurrencyDto;
import com.andersen.banking.meeting_impl.feign.dto.PaymentTypeDto;
import com.andersen.banking.meeting_impl.feign.dto.TransferRequestDto;
import com.andersen.banking.meeting_impl.feign.dto.TransferResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "transfer-service")
public interface TransferClient {

    @PostMapping("/api/v1/transfer")
    TransferResponseDto createTransfer(TransferRequestDto transferRequestDto);

    @GetMapping("/api/v1/transfer/currencies")
    List<CurrencyDto> getAllCurrencies();

    @GetMapping("/api/v1/transfer/payment_types")
    List<PaymentTypeDto> getAllPaymentTypes();
}
