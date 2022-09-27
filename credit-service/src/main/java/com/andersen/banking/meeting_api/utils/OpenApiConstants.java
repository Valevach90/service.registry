package com.andersen.banking.meeting_api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String DESCRIPTION_CREDIT_PRODUCT_ID = "Credit product id";
    public static final String DESCRIPTION_CREDIT_PRODUCT_NAME = "Credit product name";
    public static final String DESCRIPTION_MIN_SUM = "Minimal sum of credit";
    public static final String DESCRIPTION_MAX_SUM = "Maximal sum of credit";
    public static final String DESCRIPTION_CURRENCY = "Currency of credit";
    public static final String DESCRIPTION_CURRENCY_ID = "Currency id";
    public static final String DESCRIPTION_MIN_LOAN_RATE = "Minimal loan rate of credit";
    public static final String DESCRIPTION_MAX_LOAN_RATE = "Maximum loan rate of credit";
    public static final String DESCRIPTION_NEED_GUARANTEE = "Need guarantee for credit or not";
    public static final String DESCRIPTION_EARLY_REPAYMENT = "Has early repayment or not";
    public static final String DESCRIPTION_MIN_TERM = "Minimal term of credit in months";
    public static final String DESCRIPTION_MAX_TERM = "Maximum term of credit in months";
    public static final String DESCRIPTION_CREDIT_PRODUCT = "Credit product to create credit";
    public static final String DESCRIPTION_CALCULATION_MODE = "Calculation mode can be "
        + "differentiate or annuity";
    public static final String DESCRIPTION_GRACE_PERIOD = "Grace period of credit in months";
    public static final String DESCRIPTION_INCOME_STATEMENT = "Need guarantee for credit or not";

    public static final String EXAMPLE_ID = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11";
    public static final String EXAMPLE_CREDIT_PRODUCT_NAME = "Family mortgage";
    public static final String EXAMPLE_MONTHS_INTEGER = "11";
    public static final String EXAMPLE_BOOLEAN = "false";
    public static final String EXAMPLE_SUM_DECIMAL = "1234556.899290";
    public static final String EXAMPLE_CURRENCY = "USD";
    public static final String EXAMPLE_RATE_DOUBLE = "0.54";
    public static final String EXAMPLE_CREDIT_PRODUCT = "Text of credit description";
    public static final String EXAMPLE_CALCULATION_MODE = "DIFFERENTIATE";
}
