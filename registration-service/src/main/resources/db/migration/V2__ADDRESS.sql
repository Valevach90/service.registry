CREATE TABLE address
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id   BIGINT,
    zip_code INTEGER,
    country   VARCHAR(30) NOT NULL,
    region    VARCHAR(45),
    location  VARCHAR(45),
    city      VARCHAR(45),
    street    VARCHAR(45),
    house     VARCHAR(5) NOT NULL,
    building     VARCHAR(5),
    flat      VARCHAR(5),
    CONSTRAINT pk_address PRIMARY KEY (id)
);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

INSERT INTO address(user_id, zip_code, country, region, location, city, street, house, building, flat)
VALUES (3,167823,'Russia', 'St.Petersburg', 'Primorsiy', 'St.Petersburg', 'Nevskiy', '22', '1', '51'),
       (2,167144,'Russia', 'St.Petersburg', 'Primorsiy', 'St.Petersburg', 'Nevskiy', '11', '2', '52'),
       (1,123421,'Russia', 'St.Petersburg', 'Primorsiy', 'St.Petersburg', 'Nevskiy', '33', '3', '53');