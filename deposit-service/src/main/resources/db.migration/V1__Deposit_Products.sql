CREATE TABLE deposit_products
(
    id                          BIGINT                                  NOT NULL,
    deposit_name                VARCHAR(30)                             NOT NULL,
    type                        VARCHAR(30)                             NOT NULL,
    currency                    VARCHAR(3)                              NOT NULL,
    min_term_months             BIGINT                                  NOT NULL,
    max_term_months             BIGINT                                  NOT NULL,
    min_amount                  BIGINT                                  NOT NULL,
    max_amount                  BIGINT                                  NOT NULL,
    min_interest_rate           BIGINT                                  NOT NULL,
    max_interest_rate           BIGINT                                  NOT NULL,
    fixed_interest              Boolean                                         ,
    subsequent_replenishment    Boolean                                         ,
    early_withdrawal            Boolean                                         ,
    interest_withdrawal         Boolean                                         ,
    capitalization              Boolean                                         ,
    is_revocable                Boolean                                         ,
    is_customizable             Boolean                                 NOT NULL,
    is_active                   Boolean                                 NOT NULL,

    CONSTRAINT pk_deposits_products PRIMARY KEY (id)
);
