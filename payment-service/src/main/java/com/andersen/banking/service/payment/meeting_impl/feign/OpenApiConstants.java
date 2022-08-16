package com.andersen.banking.service.payment.meeting_impl.feign;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String DESCRIPTION_ADDRESS_ID = "Address Id";
    public static final String DESCRIPTION_PASSPORT_ID = "Passport Id";
    public static final String DESCRIPTION_USER_ID = "User Id";
    public static final String EXAMPLE_LONG = "10";

    public static final String DESCRIPTION_PASSPORT_SERIAL_NUMBER = "Passport serial number";
    public static final String EXAMPLE_PASSPORT_SERIAL_NUMBER = "12";

    public static final String DESCRIPTION_PASSPORT_CODE = "Passport code";
    public static final String EXAMPLE_PASSPORT_CODE = "1234567";

    public static final String DESCRIPTION_PASSPORT_BIRTHDAY = "Birthday date";
    public static final String DESCRIPTION_PASSPORT_DATE_ISSUE = "Passport date issue";
    public static final String DESCRIPTION_PASSPORT_TERMINATION_DATE = "Passport termination date";
    public static final String EXAMPLE_DATE = "2000-04-13";
    public static final String EXAMPLE_ISSUE_DATE = "2018-04-13";
    public static final String EXAMPLE_TERMINATION_DATE = "2028-04-13";

    public static final String DESCRIPTION_FIRST_NAME = "First name";
    public static final String DESCRIPTION_LAST_NAME = "Last name";
    public static final String DESCRIPTION_PATRONYMIC = "Patronymic";
    public static final String EXAMPLE_FIRST_NAME = "Bilbo";
    public static final String EXAMPLE_LAST_NAME = "Bugins";
    public static final String EXAMPLE_PATRONYMIC = "Petrovich";

    public static final String DESCRIPTION_PASSPORT_DEPARTMENT_ISSUED = "Department issued";
    public static final String DESCRIPTION_PASSPORT_BORN_PLACE = "Born Place";
    public static final String EXAMPLE_PASSPORT_DEPARTMENT_ISSUED = "Moscow Taganskiy district";
    public static final String EXAMPLE_PASSPORT_BORN_PLACE = "Minsk";

    public static final String DESCRIPTION_ZIP_CODE = "Post office index";
    public static final String EXAMPLE_ZIP_CODE = "220014";
    public static final String DESCRIPTION_COUNTRY = "Country";
    public static final String EXAMPLE_COUNTRY = "Belarus";
    public static final String DESCRIPTION_REGION = "Region";
    public static final String EXAMPLE_REGION = "Vitebsk";
    public static final String DESCRIPTION_LOCATION = "Administrative region";
    public static final String EXAMPLE_LOCATION = "Minsk";
    public static final String DESCRIPTION_EMAIL = "Email";
    public static final String EXAMPLE_EMAIL = "michail@mail.ru";

    public static final String DESCRIPTION_PHONE = "Phone";
    public static final String EXAMPLE_PHONE = "1234567890";

    public static final String DESCRIPTION_BUILDING = "Building block number";
    public static final String EXAMPLE_BUILDING = "2";
    public static final String DESCRIPTION_FLAT = "Flat";
    public static final String EXAMPLE_FLAT = "11";
    public static final String DESCRIPTION_CITY = "City";
    public static final String EXAMPLE_CITY = "Minsk";
    public static final String DESCRIPTION_STREET = "Street";
    public static final String EXAMPLE_STREET = "Komsomolskaya";
    public static final String DESCRIPTION_HOUSE = "House number";
    public static final String EXAMPLE_HOUSE = "22";

    public static final String DESCRIPTION_ADDRESS = "Address of user";
    public static final String DESCRIPTION_PASSPORT = "Passport of user";
    public static final String DESCRIPTION_USER = "User personal data";
}