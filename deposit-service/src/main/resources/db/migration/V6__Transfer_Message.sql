CREATE TABLE transfer_message
(
    transfer_id             BIGINT              NOT NULL,
    user_id                 BIGINT              NOT NULL,
    source_number           VARCHAR(32)         NOT NULL,
    source_type             VARCHAR(32)         NOT NULL,
    destination_number      VARCHAR(32)         NOT NULL,
    destination_type        VARCHAR(32)         NOT NULL,
    amount                  BIGINT              NOT NULL,
    currency_name           VARCHAR(3)          NOT NULL,

    CONSTRAINT pk_transfer_message PRIMARY KEY (transfer_id)
);