CREATE TABLE IF NOT EXISTS currency
(
    id   UUID DEFAULT gen_random_uuid() NOT NULL,
    name VARCHAR(3)                     NOT NULL,

    CONSTRAINT pk_currencies PRIMARY KEY (id)
);
INSERT INTO currency (name)
VALUES ('USD');
INSERT INTO currency (name)
VALUES ('EUR');
