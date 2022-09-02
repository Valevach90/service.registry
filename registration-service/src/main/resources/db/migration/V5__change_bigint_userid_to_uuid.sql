CREATE EXTENSION postgres_fdw;
CREATE SERVER foreign_server
    FOREIGN DATA WRAPPER postgres_fdw
    OPTIONS (host '${host}',
        port '${port}',
        dbname '${dbname}');

CREATE USER MAPPING FOR ${username}
    SERVER foreign_server
    OPTIONS (user '${username}', password '${password}');

CREATE FOREIGN TABLE foreign_table (
    id                          varchar(36),
    email                       varchar(255)
    )
    SERVER foreign_server
    OPTIONS (schema_name '${schemaname}', table_name '${tablename}');

ALTER TABLE users
    ADD COLUMN uuid UUID UNIQUE;
ALTER TABLE passport
    ADD COLUMN user_uuid UUID UNIQUE;
ALTER TABLE address
    ADD COLUMN user_uuid UUID UNIQUE;

UPDATE users
SET uuid = foreign_table.id::uuid,
    email = foreign_table.email
FROM foreign_table
WHERE foreign_table.email = users.email;

UPDATE passport
SET user_uuid = users.uuid,
    user_id = users.id
FROM users
WHERE users.id = passport.user_id;

UPDATE address
SET user_uuid = users.uuid,
    user_id = users.id
FROM users
WHERE users.id = address.user_id;


ALTER TABLE passport DROP CONSTRAINT fk_passport_on_users;
ALTER TABLE passport DROP CONSTRAINT passport_user_id_key;
ALTER TABLE address DROP CONSTRAINT fk_address_on_user;
ALTER TABLE users DROP CONSTRAINT pk_users;

ALTER TABLE users DROP COLUMN id;
ALTER TABLE passport DROP COLUMN user_id;
ALTER TABLE address DROP COLUMN user_id;

ALTER TABLE users
    RENAME COLUMN uuid TO id;
ALTER TABLE address
    RENAME COLUMN user_uuid TO user_id;
ALTER TABLE passport
    RENAME COLUMN user_uuid TO user_id;

ALTER TABLE users
    ADD PRIMARY KEY (id);

ALTER TABLE address
    ADD CONSTRAINT fk_address_on_user FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE passport
    ADD CONSTRAINT fk_passport_on_users FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE CASCADE;


DROP FOREIGN TABLE foreign_table;
DROP USER MAPPING FOR ${username} SERVER foreign_server;
DROP SERVER foreign_server;
DROP EXTENSION postgres_fdw;