CREATE TABLE passport
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id           BIGINT                                  NOT NULL,
    address_id        BIGINT                                  NOT NULL,
    serial_number     CHAR(5)                                 NOT NULL,
    passport_code     CHAR(6)                                 NOT NULL,
    division_code     CHAR(7)                                 NOT NULL,
    birthday          date                                    NOT NULL,
    date_issue        date                                    NOT NULL,
    termination_date  date                                    NOT NULL,
    first_name        VARCHAR(30)                             NOT NULL,
    last_name         VARCHAR(30)                             NOT NULL,
    patronymic        VARCHAR(30),
    born_place        VARCHAR(45),
    department_issued VARCHAR(45)                             NOT NULL,
    CONSTRAINT pk_passport PRIMARY KEY (id)
);

ALTER TABLE passport
    ADD CONSTRAINT FK_PASSPORT_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE passport
    ADD CONSTRAINT FK_PASSPORT_ON_USERS FOREIGN KEY (user_id) REFERENCES users (id);