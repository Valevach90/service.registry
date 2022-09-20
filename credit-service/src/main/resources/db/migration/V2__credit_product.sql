 CREATE TABLE IF NOT EXISTS credit_products
(
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    product_name          VARCHAR(255)                               NOT NULL,
    min_sum               DECIMAL,
    max_sum               DECIMAL,
    currency_id           UUID                                       NOT NULL,
    min_loan_rate         DECIMAL,
    max_loan_rate         DECIMAL,
    need_guarantee        BOOLEAN,
    early_repayment       BOOLEAN,
    min_term              INTEGER,
    max_term              INTEGER,
    description           TEXT,
    calculation_mode      VARCHAR(255)                               NOT NULL,
    grace_period_month    INTEGER,
    need_income_statement BOOLEAN
);




