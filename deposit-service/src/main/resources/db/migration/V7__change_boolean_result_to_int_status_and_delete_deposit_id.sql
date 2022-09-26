ALTER TABLE transfers
    ADD COLUMN status INT;

ALTER TABLE transfers ALTER COLUMN source_number TYPE varchar(255);
ALTER TABLE transfers ALTER COLUMN source_type TYPE varchar(255);
ALTER TABLE transfers ALTER COLUMN destination_number TYPE varchar(255);
ALTER TABLE transfers ALTER COLUMN destination_type TYPE varchar(255);

UPDATE transfers
SET status = 3
WHERE transfers.result = true;

UPDATE transfers
SET status = 4
WHERE transfers.result = false;

ALTER TABLE transfers
    DROP COLUMN result;

ALTER TABLE transfers
    DROP COLUMN deposit_id;