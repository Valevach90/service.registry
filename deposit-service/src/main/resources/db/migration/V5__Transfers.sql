CREATE TABLE transfers
(
    transfer_id             UUID                NOT NULL,
    user_id                 BIGINT              NOT NULL,
    deposit_id              BIGINT              NOT NULL,
    source_number           VARCHAR(32)         NOT NULL,
    source_type             VARCHAR(32)         NOT NULL,
    destination_number      VARCHAR(32)         NOT NULL,
    destination_type        VARCHAR(32)         NOT NULL,
    amount                  BIGINT              NOT NULL,
    currency_name           VARCHAR(3)          NOT NULL,
    time                    TIMESTAMP           NOT NULL,
    result                  Boolean             NOT NULL,
    status_description      VARCHAR(500)        NOT NULL,

    CONSTRAINT pk_transfers PRIMARY KEY (transfer_id)
);

ALTER TABLE transfers
    ADD CONSTRAINT fk_transfer_on_deposit FOREIGN KEY (deposit_id) REFERENCES deposits (id) ON UPDATE CASCADE;
