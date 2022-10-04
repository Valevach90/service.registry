ALTER TABLE deposit_products
    DROP COLUMN description;
ALTER TABLE deposits
    DROP COLUMN replenishment_source_number;
ALTER TABLE deposits
    DROP COLUMN withdrawal_destination_number;

CREATE TABLE linked_cards
(
    id                   UUID DEFAULT gen_random_uuid() NOT NULL,
    first_twelve_numbers VARCHAR(64)                    NOT NULL,
    last_four_numbers    VARCHAR(4)                     NOT NULL,
    deposit_id           UUID                           NOT NULL,

    CONSTRAINT pk_linked_card PRIMARY KEY (id),
    CONSTRAINT fk_linked_card_on_deposit FOREIGN KEY (deposit_id)
        REFERENCES deposits (id) ON DELETE CASCADE
);

CREATE TABLE deposit_product_descriptions
(
    id                   UUID DEFAULT gen_random_uuid() NOT NULL,
    short_description    VARCHAR(100)                   NOT NULL,
    full_description     VARCHAR(500)                           ,
    deposit_product_id   UUID                           NOT NULL,

        CONSTRAINT pk_linked_card PRIMARY KEY (id),
        CONSTRAINT fk_description_on_deposit_product FOREIGN KEY (deposit_product_id)
            REFERENCES deposit_products (id) ON DELETE CASCADE
);
