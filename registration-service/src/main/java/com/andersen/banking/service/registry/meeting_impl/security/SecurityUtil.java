package com.andersen.banking.service.registry.meeting_impl.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String EMPLOYEE = "ROLE_EMPLOYEE";
}
