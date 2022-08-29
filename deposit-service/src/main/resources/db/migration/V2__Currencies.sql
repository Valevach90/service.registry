CREATE TABLE currencies
(
    id                          BIGINT,
    name                        VARCHAR(3)                             NOT NULL,

    CONSTRAINT pk_currencies PRIMARY KEY (id)
);

INSERT INTO currencies (id, name)
VALUES (1, 'EUR');
INSERT INTO currencies (id, name)
VALUES (2, 'USD');