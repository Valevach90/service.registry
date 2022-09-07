UPDATE payment_service.type_card
SET type_name='SILVER'
WHERE type_name = 'STANDARD';

CREATE UNIQUE INDEX type_card_multi_idx ON payment_service.type_card (payment_system, type_name);