CREATE TABLE IF NOT EXISTS revinfo
(
    id       INTEGER PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    revtstmp BIGINT
);

CREATE TABLE IF NOT EXISTS account_aud
(
    id             UUID        NOT NULL,
    owner_id       UUID        NOT NULL,
    open_date      DATE        NOT NULL,
    close_date     DATE,
    bank_name      VARCHAR(30) NOT NULL,
    account_number VARCHAR(34) NOT NULL,
    currency       VARCHAR(3)  NOT NULL,
    balance        BIGINT      NOT NULL,
    is_active      BOOLEAN     NOT NULL,
    rev            INTEGER     NOT NULL,
    revtype        INTEGER     NOT NULL,
    PRIMARY KEY (id, rev),

    CONSTRAINT account_aud_revinfo_id_fkey
        FOREIGN KEY (rev) REFERENCES revinfo (id)
);