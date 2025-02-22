CREATE TABLE IF NOT EXISTS payment_service.account
(
    id             UUID DEFAULT gen_random_uuid() NOT NULL,
    owner_id       UUID                           NOT NULL,
    open_date      DATE                           NOT NULL,
    close_date     DATE,
    bank_name      VARCHAR(30)                    NOT NULL,
    account_number VARCHAR(34)                    NOT NULL,
    currency       VARCHAR(3)                     NOT NULL,
    balance        BIGINT                         NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

