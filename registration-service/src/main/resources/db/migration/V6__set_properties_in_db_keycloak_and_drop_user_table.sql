CREATE EXTENSION postgres_fdw;
CREATE SERVER foreign_server
    FOREIGN DATA WRAPPER postgres_fdw
    OPTIONS (host '${host}',
        port '${port}',
        dbname '${dbname}');

CREATE USER MAPPING FOR ${setusername}
    SERVER foreign_server
    OPTIONS (user '${username}', password '${password}');

CREATE FOREIGN TABLE foreign_table_att (
    name varchar(255),
    value varchar(255),
    user_id varchar(36),
    id UUID DEFAULT gen_random_uuid()
    )
    SERVER foreign_server
    OPTIONS (schema_name '${schemaname}', table_name '${tablenameattribute}');

CREATE FOREIGN TABLE foreign_table_user_entity (
    id varchar(36)
    )
    SERVER foreign_server
    OPTIONS (schema_name '${schemaname}', table_name '${tablenameattribute}');


INSERT INTO foreign_table_att (name, value, user_id)
SELECT 'phone', phone, id
FROM public.users
WHERE phone NOTNULL AND EXISTS(
        SELECT id
        FROM foreign_table_user_entity ftue
        WHERE users.id = ftue.id::uuid
    );

INSERT INTO foreign_table_att (name, value, user_id)
SELECT 'patronymic', patronymic, id
FROM postgres.public.users
WHERE patronymic NOTNULL AND EXISTS(
    SELECT id
    FROM foreign_table_user_entity ftue
    WHERE users.id = ftue.id::uuid
    );

DROP FOREIGN TABLE foreign_table_att;
DROP FOREIGN TABLE foreign_table_user_entity;
DROP USER MAPPING FOR ${setusername} SERVER foreign_server;
DROP SERVER foreign_server;
DROP EXTENSION postgres_fdw;

ALTER TABLE public.passport
    DROP CONSTRAINT fk_passport_on_users;
ALTER TABLE public.address
    DROP CONSTRAINT fk_address_on_user;

DROP TABLE public.users;