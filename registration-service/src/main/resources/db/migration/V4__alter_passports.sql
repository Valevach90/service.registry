TRUNCATE TABLE passport
CASCADE;
TRUNCATE TABLE address
CASCADE;
TRUNCATE TABLE users
CASCADE;

ALTER TABLE passport
DROP COLUMN division_code;

ALTER TABLE passport
ALTER COLUMN serial_number TYPE CHAR(2);

ALTER TABLE passport
ALTER COLUMN passport_code TYPE CHAR(7);