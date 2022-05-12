package com.andersen.banking.service.registry.meeting_api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String DESCRIPTION_USER_ID = "User id";
    public static final String EXAMPLE_LONG = "10";

    public static final String DESCRIPTION_FIRST_NAME = "First name";
    public static final String EXAMPLE_FIRST_NAME = "Михаил";

    public static final String DESCRIPTION_LAST_NAME = "Last name";
    public static final String EXAMPLE_LAST_NAME = "Романов";

    public static final String DESCRIPTION_PATRONYMIC = "Patronymic";
    public static final String EXAMPLE_PATRONYMIC = "Александрович";

    public static final String DESCRIPTION_EMAIL = "Email";
    public static final String EXAMPLE_EMAIL = "michail@mail.ru";

    public static final String DESCRIPTION_PHONE = "Phone";
    public static final String EXAMPLE_PHONE = "555-55-55";

    public static final String HTTP_NOT_FOUND = "404";

    public static final String DESCRIPTION_ERROR = "Error description";

    public static final String EXAMPLE_ERROR_NOT_FOUND = "Not found";

    public static final String DESCRIPTION_ERROR_CODE = "Error code";

}
