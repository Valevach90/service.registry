ALTER TABLE payment_service.regular_payment
    RENAME COLUMN first_date TO start_date;

ALTER TABLE payment_service.regular_payment
    RENAME COLUMN last_date TO next_date;