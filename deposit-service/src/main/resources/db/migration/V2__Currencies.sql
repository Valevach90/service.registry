CREATE TABLE currencies
(
    id                          BIGINT,
    name                        VARCHAR(3)                             NOT NULL,

    CONSTRAINT pk_currencies PRIMARY KEY (id)
);
