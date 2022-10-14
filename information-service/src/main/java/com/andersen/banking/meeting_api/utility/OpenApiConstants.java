package com.andersen.banking.meeting_api.utility;

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

    public static final String EXAMPLE_LONG = "10";
    public static final String EXAMPLE_FILE_NAME = "Document.doc";
    public static final String EXAMPLE_LINK = "https://www.documents.com/Document.doc";
    public static final String EXAMPLE_DATE = "2022-07-06 13:33:29,573";

    public static final String EXAMPLE_UUID = "84e7ffe0-04bf-40e8-b6ab-ef7b7ba3dc09";

}
