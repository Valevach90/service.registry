TRUNCATE TABLE notifications
CASCADE;

ALTER TABLE notifications
ALTER COLUMN code DROP NOT NULL;

ALTER TABLE notifications
ADD status VARCHAR(255) NOT NULL;