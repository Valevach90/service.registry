ALTER TABLE payment_service.card_product
    ADD COLUMN bank_partners_mini VARCHAR(40);

ALTER TABLE payment_service.card_product
    ADD COLUMN loyalty_program_mini VARCHAR(40);