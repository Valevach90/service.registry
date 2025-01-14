CREATE TABLE payment_service.card
(
    id                   UUID DEFAULT gen_random_uuid() NOT NULL,
    account_id           UUID                           NOT NULL,
    first_twelve_numbers VARCHAR(64)                    NOT NULL,
    last_four_numbers    VARCHAR(4)                     NOT NULL,
    valid_from_date      DATE                           NOT NULL,
    expire_date          DATE,
    holder_name          VARCHAR(255)                   NOT NULL,
    CONSTRAINT pk_card PRIMARY KEY (id),
    CONSTRAINT check_pin CHECK (char_length(last_four_numbers) = 4),
    CONSTRAINT card_account_id_fkey FOREIGN KEY (account_id)
        REFERENCES payment_service.account (id)
        ON DELETE CASCADE
);