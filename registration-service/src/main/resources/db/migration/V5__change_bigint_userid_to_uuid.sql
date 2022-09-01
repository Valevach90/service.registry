ALTER TABLE passport DROP CONSTRAINT fk_passport_on_users;
ALTER TABLE passport DROP CONSTRAINT passport_user_id_key;
ALTER TABLE address DROP CONSTRAINT fk_address_on_user;
ALTER TABLE users DROP CONSTRAINT pk_users;

ALTER TABLE users DROP COLUMN id;
ALTER TABLE passport DROP COLUMN user_id;
ALTER TABLE address DROP COLUMN user_id;

ALTER TABLE users
    ADD COLUMN id UUID UNIQUE;
ALTER TABLE passport
    ADD COLUMN user_id UUID UNIQUE;
ALTER TABLE address
    ADD COLUMN user_id UUID;

ALTER TABLE users
    ADD PRIMARY KEY (id);

ALTER TABLE address
    ADD CONSTRAINT fk_address_on_user FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE passport
    ADD CONSTRAINT fk_passport_on_users FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE CASCADE;
