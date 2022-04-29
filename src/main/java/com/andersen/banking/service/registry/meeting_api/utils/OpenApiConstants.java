package com.andersen.banking.service.registry.meeting_api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String HTTP_BAD_REQUEST = "400";
    public static final String HTTP_UNAUTHORIZED = "401";
    public static final String HTTP_FORBIDDEN = "403";
    public static final String HTTP_NOT_FOUND = "404";
    public static final String HTTP_METHOD_NOT_ALLOWED = "405";
    public static final String HTTP_CONFLICT = "409";
    public static final String EXAMPLE_ERROR_NOT_FOUND = "Not found";
    public static final String DESCRIPTION_ERROR_CODE = "Error code";
    public static final String DESCRIPTION_ERROR_DESCRIPTION = "Error description";
}
