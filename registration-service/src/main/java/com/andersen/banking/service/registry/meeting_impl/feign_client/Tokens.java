package com.andersen.banking.service.registry.meeting_impl.feign_client;

import lombok.Data;

@Data
public class Tokens {
    private String accessToken;

    private String refreshToken;
}
