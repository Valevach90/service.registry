ALTER TABLE payment_service.card
    ADD COLUMN is_active BOOLEAN;

UPDATE payment_service.card
    SET is_active = true WHERE is_active is null;

ALTER TABLE payment_service.card
    ALTER COLUMN is_active SET NOT NULL;

ALTER TABLE payment_service.account
    ADD COLUMN is_active BOOLEAN;

UPDATE payment_service.account
    SET is_active = true WHERE is_active is null;

ALTER TABLE payment_service.account
    ALTER COLUMN is_active SET NOT NULL;