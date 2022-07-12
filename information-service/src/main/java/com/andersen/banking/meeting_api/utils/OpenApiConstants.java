package com.andersen.banking.meeting_api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String DESCRIPTION_FILE_INFO_ID = "File information id";
    public static final String DESCRIPTION_FILE_NAME = "Name of file";
    public static final String DESCRIPTION_LINK = "Download link";
    public static final String DESCRIPTION_DATE_OF_CREATION = "Date of file creation";
    public static final String DESCRIPTION_DATE_OF_UPDATE = "Date of file update";

    public static final String EXAMPLE_STRING_ID = "28848ed9-d935-4f4c-97d2-01d2ab9d477c";
    public static final String EXAMPLE_FILE_NAME = "Document.doc";
    public static final String EXAMPLE_LINK = "https://www.documents.com/Document.doc";
    public static final String EXAMPLE_DATE = "2022-07-06 13:33:29,573";
}
