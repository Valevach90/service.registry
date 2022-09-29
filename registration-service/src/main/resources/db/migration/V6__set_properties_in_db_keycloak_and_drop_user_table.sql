CREATE EXTENSION postgres_fdw;
CREATE SERVER foreign_server
    FOREIGN DATA WRAPPER postgres_fdw
    OPTIONS (host '${host}',
        port '${port}',
        dbname '${dbname}');

CREATE USER MAPPING FOR ${setusername}
    SERVER foreign_server
    OPTIONS (user '${username}', password '${password}');

CREATE FOREIGN TABLE foreign_table (
    name varchar(255),
    value varchar(255),
    user_id varchar(36),
    id UUID DEFAULT gen_random_uuid()
    )
    SERVER foreign_server
    OPTIONS (schema_name '${schemaname}', table_name '${tablenameattribute}');

INSERT INTO foreign_table (name, value, user_id)
SELECT 'phone', phone, id
FROM public.users
WHERE phone NOTNULL;

INSERT INTO foreign_table (name, value, user_id)
SELECT 'patronymic', patronymic, id
FROM postgres.public.users
WHERE patronymic NOTNULL;

DROP FOREIGN TABLE foreign_table;
DROP USER MAPPING FOR ${setusername} SERVER foreign_server;
DROP SERVER foreign_server;
DROP EXTENSION postgres_fdw;

ALTER TABLE public.passport
    DROP CONSTRAINT fk_passport_on_users;
ALTER TABLE public.address
    DROP CONSTRAINT fk_address_on_user;

DROP TABLE public.users;