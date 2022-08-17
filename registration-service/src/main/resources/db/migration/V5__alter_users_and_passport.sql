ALTER TABLE users
ALTER COLUMN patronymic
DROP NOT NULL;

ALTER TABLE passport
ADD CONSTRAINT user_id_unique UNIQUE (user_id);
