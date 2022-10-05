package com.andersen.banking.meeting_impl.feign;

import com.andersen.banking.meeting_impl.feign.dto.TransferRequestDto;
import com.andersen.banking.meeting_impl.feign.dto.TransferResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "api-gateway-transfer")
public interface TransferClient {

    @PostMapping("/api/v1/transfer")
    TransferResponseDto createTransfer(TransferRequestDto transferRequestDto);
}
