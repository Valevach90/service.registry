package com.andersen.banking.meeting_api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {
    public static final String EXAMPLE_PAYMENT_SYSTEM = "VISA";
    public static final String EXAMPLE_TYPECARD = "SILVER";
    public static final String EXAMPLE_UUID = "0d4ff469-465e-412b-9737-34d08d227464";
    public static final String EXAMPLE_ACCOUNT_NUMBER = "12345678";
    public static final String EXAMPLE_BANKNAME = "BankName";
    public static final String EXAMPLE_CURRENCY = "USD";
    public static final String EXAMPLE_BALANCE = "10000";
    public static final String EXAMPLE_FIRST_NAME = "Bilbo";
    public static final String EXAMPLE_LAST_NAME = "Bugins";
    public static final String EXAMPLE_NAME = EXAMPLE_FIRST_NAME + " " + EXAMPLE_LAST_NAME;
}