package com.andersen.banking.service.registry.meeting_api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String DESCRIPTION_ADDRESS_ID = "Address Id";

    public static final String DESCRIPTION_USER_ID = "User Id";
    public static final String EXAMPLE_LONG = "10";

    public static final String DESCRIPTION_ZIPO_CODE = "Post office index";
    public static final String EXAMPLE_ZIPO_CODE = "220014";

    public static final String DESCRIPTION_COUNTRY = "Country";
    public static final String EXAMPLE_COUNTRY = "Belarus";

    public static final String DESCRIPTION_REGION = "Region";
    public static final String EXAMPLE_REGION = "Vitebsk";

    public static final String DESCRIPTION_LOCATION = "Administrative region";
    public static final String EXAMPLE_LOCATION = "Minsk";

    public static final String DESCRIPTION_CITY = "City";
    public static final String EXAMPLE_CITY = "Minsk";

    public static final String DESCRIPTION_STREET = "Street";
    public static final String EXAMPLE_STREET = "Komsomolskaya";

    public static final String DESCRIPTION_HOUSE = "House number";
    public static final String EXAMPLE_HOUSE = "22";

    public static final String DESCRIPTION_BUILD = "Building block number";
    public static final String EXAMPLE_BUILD = "2";

    public static final String DESCRIPTION_FLAT = "Flat";
    public static final String EXAMPLE_FLAT = "11";

    public static final String HTTP_BAD_REQUEST = "400";
    public static final String HTTP_UNAUTHORIZED = "401";
    public static final String HTTP_FORBIDDEN = "403";
    public static final String HTTP_NOT_FOUND = "404";
    public static final String HTTP_METHOD_NOT_ALLOWED = "405";
    public static final String HTTP_CONFLICT = "409";


    public static final String DESCRIPTION_ERROR_DESCRIPTION = "Error description";
    public static final String EXAMPLE_ERROR_BAD_REQUEST = "Bad request";
    public static final String EXAMPLE_ERROR_UNAUTHORIZED = "Unauthorized";
    public static final String EXAMPLE_ERROR_NOT_FOUND = "Not found";
    public static final String EXAMPLE_ERROR_CONFLICT = "Conflict";
    public static final String EXAMPLE_ERROR_FORBIDDEN = "Forbidden";
    public static final String EXAMPLE_ERROR_METHOD_NOT_ALLOWED = "Method not allowed";

    public static final String DESCRIPTION_ERROR_CODE = "Error code";
}
