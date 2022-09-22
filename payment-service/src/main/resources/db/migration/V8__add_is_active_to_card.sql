ALTER TABLE payment_service.card
    ADD COLUMN is_active BOOLEAN;

UPDATE payment_service.card
    SET is_active = true WHERE is_active is null;

ALTER TABLE payment_service.card
    ALTER COLUMN is_active SET NOT NULL;