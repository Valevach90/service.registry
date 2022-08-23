CREATE TABLE deposit_types
(
    id                          BIGINT,
    name                        VARCHAR(30)                             NOT NULL,

    CONSTRAINT pk_deposit_types PRIMARY KEY (id)
);

INSERT INTO deposit_types (id, name)
VALUES (1, 'Deposit');
INSERT INTO deposit_types (id, name)
VALUES (2, 'Saving account')
;
