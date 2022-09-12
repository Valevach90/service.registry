package com.andersen.banking.deposit_api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for open api.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String DESCRIPTION_DEPOSIT_PRODUCT_ID = "Deposit Product Id";
    public static final String DESCRIPTION_DEPOSIT_ID = "Deposit Id";
    public static final String DESCRIPTION_DEPOSIT_TYPE_ID = "Deposit Type Id";
    public static final String DESCRIPTION_CURRENCY_ID = "Currency Id";
    public static final String DESCRIPTION_TRANSFER_ID = "Transfer Id";
    public static final String DESCRIPTION_USER_ID = "User Id";
    public static final String DESCRIPTION_DEPOSIT_NUMBER = "Deposit Number";
    public static final String DESCRIPTION_DEPOSIT_NAME = "Deposit Name";
    public static final String DESCRIPTION_DEPOSIT_TYPE_NAME = "Deposit Type Name";
    public static final String DESCRIPTION_CURRENCY = "Currency of deposit";
    public static final String DESCRIPTION_CURRENCY_NAME = "Currency Name";
    public static final String DESCRIPTION_DEPOSIT_PRODUCT = "Deposit Product for creating Deposits";
    public static final String DESCRIPTION_TRANSFERS = "List transfers of deposit";
    public static final String EXAMPLE_DEPOSIT_NAME = "Deposit for All";
    public static final String EXAMPLE_DEPOSIT_TYPE_NAME = "Deposit";
    public static final String EXAMPLE_CURRENCY_NAME = "EUR";
    public static final String DESCRIPTION_DEPOSIT_TYPE = "Deposit Type";
    public static final String EXAMPLE_DEPOSIT_TYPE = "Deposit";

    public static final String DESCRIPTION_DATE = "Date";
    public static final String DESCRIPTION_OPEN_DATE = "Open date";
    public static final String DESCRIPTION_CLOSE_DATE = "Close date";
    public static final String EXAMPLE_DATE = "2022-06-29";

    public static final String DESCRIPTION_TERM_MONTHS = "Term of Deposit in Months";
    public static final String DESCRIPTION_MIN_TERM_MONTHS = "Minimal Term of Deposit in Months";
    public static final String DESCRIPTION_MAX_TERM_MONTHS = "Maximal Term of Deposit in Months";
    public static final String DESCRIPTION_AMOUNT = "Amount of Deposit or Transfer";
    public static final String DESCRIPTION_MIN_AMOUNT = "Minimal Amount of Deposit";
    public static final String DESCRIPTION_MAX_AMOUNT = "Maximal Amount of Deposit";
    public static final String DESCRIPTION_INTEREST_RATE = "Interest Rate of Deposit";
    public static final String DESCRIPTION_MIN_INTEREST_RATE = "Minimal Interest Rate of Deposit";
    public static final String DESCRIPTION_MAX_INTEREST_RATE = "Maximal Interest Rate of Deposit";

    public static final String DESCRIPTION_FIXED_INTEREST = "Fixed Interest option";
    public static final String DESCRIPTION_SUBSEQUENT_REPLENISHMENT = "Subsequent Replenishment option";
    public static final String DESCRIPTION_EARLY_WITHDRAWAL = "Early Withdrawal option";
    public static final String DESCRIPTION_INTEREST_WITHDRAWAL = "Interest Withdrawal option";
    public static final String DESCRIPTION_CAPITALIZATION = "Capitalization option";
    public static final String DESCRIPTION_IS_REVOCABLE = "Revocation option";
    public static final String DESCRIPTION_IS_CUSTOMIZABLE = "Customization option";
    public static final String DESCRIPTION_IS_ACTIVE = "Activation option";
    public static final String DESCRIPTION_RESULT_OF_TRANSFER = "Result of transfer";
    public static final String DESCRIPTION_SOURCE_NUMBER = "Source Number for Replenishment";
    public static final String DESCRIPTION_SOURCE_TYPE = "Type of Source for Replenishment";
    public static final String DESCRIPTION_DESTINATION_NUMBER = "Destination Number for Withdrawal";
    public static final String DESCRIPTION_DESTINATION_TYPE = "Type of Destination for Withdrawal";
    public static final String DESCRIPTION_STATUS_OF_TRANSFER = "Description of transfer status";
    public static final String EXAMPLE_TRANSFER_STATUS_DESCRIPTION = "Transfer is successful";
    public static final String DESCRIPTION_TIME = "Time of an event";
    public static final String EXAMPLE_TIME = "2022-07-06 13:33:29,573";
    public static final String EXAMPLE_UUID = "f3ed2183-5082-4ac3-bfc6-086d93e42479";
    public static final String EXAMPLE_INTEGER = "10";
    public static final String EXAMPLE_BOOLEAN = "true";
    public static final String EXAMPLE_STRING_NUMBER = "1234567890123456";
    public static final String DESCRIPTION_ERROR = "Error description";
    public static final String EXAMPLE_ERROR_NOT_FOUND = "Not found";
    public static final String DESCRIPTION_ERROR_CODE = "Error code";
    public static final String HTTP_NOT_FOUND = "404";
}
