package com.andersen.banking.meeting_impl.feign;

import com.andersen.banking.meeting_impl.feign.dto.PersonalDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "api-gateway")
public interface RegistrationClient {

    @GetMapping("/api/v1/users/data")
    PersonalDataDto getUserPersonalData();
}