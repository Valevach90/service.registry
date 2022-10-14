CREATE TABLE atm
(
    id                      UUID UNIQUE DEFAULT gen_random_uuid()   NOT NULL,
    created_date            TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    created_by              VARCHAR(255)                            NOT NULL,
    last_modified_date      TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    last_modified_by        VARCHAR(255)                            NOT NULL,
    deleted                 BOOLEAN                                 NOT NULL,
    street_id               BIGINT                                  NOT NULL,
    house_number            BIGINT                                  NOT NULL,
    building                VARCHAR(255),
    branch_id               BIGINT,
    round_the_clock         BOOLEAN                                 NOT NULL,
    work_at_weekend         BOOLEAN                                 NOT NULL,
    cash_withdraw           BOOLEAN                                 NOT NULL,
    currency_exchange       BOOLEAN                                 NOT NULL,
    is_active               BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_atm PRIMARY KEY (id)
);

ALTER TABLE atm
    ADD CONSTRAINT fk_atm_on_street FOREIGN KEY (street_id) REFERENCES street (id),
    ADD CONSTRAINT fk_atm_on_bank_branch FOREIGN KEY (branch_id) REFERENCES bank_branch(id);
