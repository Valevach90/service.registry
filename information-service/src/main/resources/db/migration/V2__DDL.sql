-- documents DDL

CREATE TABLE file_info
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    file_name               VARCHAR(255)                            NOT NULL,
    link                    VARCHAR(255)                            NOT NULL,
    date_of_creation        TIMESTAMP                               NOT NULL,
    date_of_update          TIMESTAMP,
    CONSTRAINT pk_file_info PRIMARY KEY (id)
);


-- countries, cities DDL

CREATE TABLE country
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255)                            NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255)                            NOT NULL,
    deleted            BOOLEAN                                 NOT NULL,
    country_name       VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_country PRIMARY KEY (id)
);

CREATE TABLE city
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255)                            NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255)                            NOT NULL,
    deleted            BOOLEAN                                 NOT NULL,
    country_id         BIGINT                                  NOT NULL,
    city_name          VARCHAR(20)                             NOT NULL,
    CONSTRAINT pk_city PRIMARY KEY (id)
);

ALTER TABLE city
    ADD CONSTRAINT FK_CITY_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES country (id);

CREATE TABLE street
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255)                            NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255)                            NOT NULL,
    deleted            BOOLEAN                                 NOT NULL,
    city_id            BIGINT                                  NOT NULL,
    street_name        VARCHAR(50)                             NOT NULL,
    CONSTRAINT pk_street PRIMARY KEY (id)
);

ALTER TABLE street
    ADD CONSTRAINT FK_STREET_ON_CITY FOREIGN KEY (city_id) REFERENCES city (id);


CREATE TABLE address
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255)                            NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255)                            NOT NULL,
    deleted            BOOLEAN                                 NOT NULL,
    street_id          BIGINT                                  NOT NULL,
    house              VARCHAR(5)                              NOT NULL,
    building           VARCHAR(5),
    CONSTRAINT pk_address PRIMARY KEY (id)
);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_STREET FOREIGN KEY (street_id) REFERENCES street (id);


CREATE TABLE bank_branch
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255)                            NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255)                            NOT NULL,
    deleted            BOOLEAN                                 NOT NULL,
    address_id         BIGINT                                  NOT NULL,
    branch_number      VARCHAR(5)                              NOT NULL,
    branch_coordinates VARCHAR(20)                             NOT NULL,
    work_at_weekend    BOOLEAN                                 NOT NULL,
    cash_withdraw      BOOLEAN                                 NOT NULL,
    money_transfer     BOOLEAN                                 NOT NULL,
    accept_payment     BOOLEAN                                 NOT NULL,
    currency_exchange  BOOLEAN                                 NOT NULL,
    exotic_currency    BOOLEAN                                 NOT NULL,
    ramp               BOOLEAN                                 NOT NULL,
    replenish_card     BOOLEAN                                 NOT NULL,
    replenish_account  BOOLEAN                                 NOT NULL,
    consultation       BOOLEAN                                 NOT NULL,
    insurance          BOOLEAN                                 NOT NULL,
    is_closed          BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_bank_branch PRIMARY KEY (id)
);

ALTER TABLE bank_branch
    ADD CONSTRAINT FK_BANK_BRANCH_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);


CREATE TABLE time_table
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by         VARCHAR(255)                            NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_by   VARCHAR(255)                            NOT NULL,
    deleted            BOOLEAN                                 NOT NULL,
    address_id         BIGINT                                  NOT NULL,
    week_day_from      VARCHAR(255)                            NOT NULL,
    week_day_to        VARCHAR(255)                            NOT NULL,
    time_from          VARCHAR(255)                            NOT NULL,
    time_to            VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_time_table PRIMARY KEY (id)
);

ALTER TABLE time_table
    ADD CONSTRAINT FK_TIME_TABLE_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);
