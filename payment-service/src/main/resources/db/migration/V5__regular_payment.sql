CREATE TABLE payment_service.regular_payment
(
    id                  UUID DEFAULT gen_random_uuid() NOT NULL,
    payment_description VARCHAR(255)                   NOT NULL,
    first_date          TIMESTAMP WITHOUT TIME ZONE    NOT NULL,
    last_date           TIMESTAMP WITHOUT TIME ZONE    NOT NULL,
    source_card_id      UUID                           NOT NULL,
    recipient_card_id   UUID                           NOT NULL,
    amount              BIGINT                         NOT NULL,
    frequency           VARCHAR(255)                   NOT NULL,
    CONSTRAINT pk_regular_payment_ PRIMARY KEY (id)
);