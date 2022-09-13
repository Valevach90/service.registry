CREATE TABLE transfer_log
(
    id                       UUID         NOT NULL,
    user_id                  UUID         NOT NULL,
    source_payment_type      VARCHAR(255) NOT NULL,
    source_number            VARCHAR(255) NOT NULL,
    destination_payment_type VARCHAR(255) NOT NULL,
    destination_number       VARCHAR(255) NOT NULL,
    amount                   BIGINT       NOT NULL,
    currency                 VARCHAR(255) NOT NULL,
    status                   INTEGER      NOT NULL,
    CONSTRAINT pk_transfer_log PRIMARY KEY (id)
);