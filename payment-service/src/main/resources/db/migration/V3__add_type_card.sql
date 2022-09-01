CREATE TABLE payment_service.type_card
(
    id             UUID DEFAULT gen_random_uuid() NOT NULL,
    payment_system varchar(64)                    NOT NULL,
    type_name      varchar(64)                    NOT NULL,
    CONSTRAINT pk_payment_system PRIMARY KEY (id)
);

INSERT INTO payment_service.type_card (payment_system, type_name)
VALUES ('VISA', 'STANDARD'),
       ('VISA', 'GOLD'),
       ('VISA', 'PLATINUM'),
       ('MASTERCARD', 'STANDARD'),
       ('MASTERCARD', 'GOLD'),
       ('MASTERCARD', 'PLATINUM');

ALTER TABLE payment_service.card
    ADD COLUMN type_card_id INT;

UPDATE payment_service.card
SET type_card_id=1;

ALTER TABLE payment_service.card
    ALTER COLUMN type_card_id SET NOT NULL;

ALTER TABLE payment_service.card
    ADD FOREIGN KEY (type_card_id) REFERENCES type_card (id);



