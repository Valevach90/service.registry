CREATE TABLE users
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name VARCHAR(255)                            NOT NULL,
    last_name  VARCHAR(255)                            NOT NULL,
    patronymic VARCHAR(255)                                    ,
    email      VARCHAR(255)                     UNIQUE NOT NULL,
    phone      VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);