CREATE TABLE currency
(
    id                 UUID DEFAULT gen_random_uuid() NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255)                   NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255)                   NOT NULL,
    deleted            BOOLEAN                        NOT NULL,
    name               VARCHAR(255)                   NOT NULL,
    description        VARCHAR(255)                   NOT NULL,
    CONSTRAINT pk_currency PRIMARY KEY (id)
);

CREATE TABLE payment_type
(
    id                 UUID DEFAULT gen_random_uuid() NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255)                   NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255)                   NOT NULL,
    deleted            BOOLEAN                        NOT NULL,
    name               VARCHAR(255)                   NOT NULL,
    description        VARCHAR(255)                   NOT NULL,
    CONSTRAINT pk_payment_type PRIMARY KEY (id)
);

CREATE TABLE transaction_status
(
    id                 UUID         NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255) NOT NULL,
    deleted            BOOLEAN      NOT NULL,
    name               VARCHAR(255) NOT NULL,
    description        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_transaction_status PRIMARY KEY (id)
);


CREATE TABLE transfer
(
    id                          UUID         NOT NULL,
    created_date                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by                  VARCHAR(255) NOT NULL,
    last_modified_date          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by            VARCHAR(255) NOT NULL,
    deleted                     BOOLEAN      NOT NULL,
    user_id                     BIGINT       NOT NULL,
    source_payment_type_id      UUID         NOT NULL,
    source_number               VARCHAR(255) NOT NULL,
    destination_payment_type_id UUID         NOT NULL,
    destination_number          VARCHAR(255) NOT NULL,
    amount                      BIGINT       NOT NULL,
    currency_id                 UUID         NOT NULL,
    status                      INTEGER      NOT NULL,
    CONSTRAINT pk_transfer PRIMARY KEY (id)
);

ALTER TABLE transfer
    ADD CONSTRAINT FK_TRANSFER_ON_CURRENCY FOREIGN KEY (currency_id) REFERENCES currency (id);

ALTER TABLE transfer
    ADD CONSTRAINT FK_TRANSFER_ON_DESTINATION_PAYMENT_TYPE FOREIGN KEY (destination_payment_type_id) REFERENCES payment_type (id);

ALTER TABLE transfer
    ADD CONSTRAINT FK_TRANSFER_ON_SOURCE_PAYMENT_TYPE FOREIGN KEY (source_payment_type_id) REFERENCES payment_type (id);


INSERT INTO currency (created_by, created_date, deleted, last_modified_by, last_modified_date, description, name)
VALUES (current_user, now(), false, current_user, now(), 'United States Dollar', 'USD');
INSERT INTO currency (created_by, created_date, deleted, last_modified_by, last_modified_date, description, name)
VALUES (current_user, now(), false, current_user, now(), 'European Union', 'EURO');

INSERT INTO payment_type (created_by, created_date, deleted, last_modified_by, last_modified_date, description, name)
VALUES (current_user, now(), false, current_user, now(), 'A payment card issued by a bank.', 'CARD');
INSERT INTO payment_type (created_by, created_date, deleted, last_modified_by, last_modified_date, description, name)
VALUES (current_user, now(), false, current_user, now(), 'A deposit is money held by a bank', 'DEPOSIT');
