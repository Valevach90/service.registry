ALTER TABLE payment_service.card
    ADD COLUMN is_active BOOLEAN;

ALTER TABLE payment_service.card
    SET is_active = TRUE WHERE is_active IS NULL;

ALTER TABLE payment_service.card
    ALTER COLUMN is_active SET NOT NULL;