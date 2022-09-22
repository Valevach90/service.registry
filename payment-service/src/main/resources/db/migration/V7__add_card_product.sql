CREATE TABLE payment_service.card_product
(
    id               UUID DEFAULT gen_random_uuid() NOT NULL,
    type_card_id     UUID                           NOT NULL,
    cashback         INT                            NOT NULL,
    price            DOUBLE PRECISION,
    advantages       VARCHAR(255)                   NOT NULL,
    bank_partners    VARCHAR(255)                   NOT NULL,
    loyalty_program  VARCHAR(255)                   NOT NULL,
    CONSTRAINT pk_card_product PRIMARY KEY (id),
    CONSTRAINT card_product_type_card_id_fk FOREIGN KEY (type_card_id)
            REFERENCES payment_service.type_card (id)
);

INSERT INTO payment_service.card_product (id ,type_card_id, cashback, price, advantages, bank_partners, loyalty_program)
    VALUES ('31b58c66-b717-475a-950a-b934f27fdca8',
            (SELECT id FROM payment_service.type_card WHERE payment_system = 'VISA' AND type_name = 'SILVER'),
            1,
            9.99,
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore ',
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore ',
            'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore '
            );

ALTER TABLE payment_service.card
    ADD COLUMN card_product_id UUID;

UPDATE payment_service.card
    SET card_product_id='31b58c66-b717-475a-950a-b934f27fdca8' WHERE card_product_id is null;

ALTER TABLE payment_service.card
    ALTER COLUMN card_product_id SET NOT NULL;

ALTER TABLE payment_service.card
    ADD FOREIGN KEY (card_product_id) REFERENCES card_product (id);

/*
    Rewiring type_card_id column from card to card_product table
*/
ALTER TABLE payment_service.card
    DROP COLUMN type_card_id;

