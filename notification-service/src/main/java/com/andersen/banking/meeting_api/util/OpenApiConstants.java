package com.andersen.banking.meeting_api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String DESCRIPTION_NOTIFICATION_CODE = "Code sent in notification";
    public static final String EXAMPLE_NOTIFICATION_CODE = "0001";
    public static final String DESCRIPTION_NOTIFICATION_TIME = "Time of notification creation";
    public static final String EXAMPLE_TIME = "2022-07-06 13:33:29,573";
    public static final String DESCRIPTION_NOTIFICATION_STATUS = "Status of notification";
    public static final String EXAMPLE_NOTIFICATION_STATUS = "Sent";


    public static final String DESCRIPTION_EMAIL = "Email";
    public static final String EXAMPLE_EMAIL = "michail@mail.ru";
}
