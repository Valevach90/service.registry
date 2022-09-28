CREATE TABLE delivery_type
(
    id                    UUID DEFAULT gen_random_uuid() NOT NULL,
    type_name             VARCHAR(30)                    NOT NULL,

    CONSTRAINT pk_delivery_type PRIMARY KEY (id)
);

INSERT INTO delivery_type (type_name)
VALUES ('COURIER'),
       ('BRANCH');

CREATE TABLE delivery_country
(
    id                    UUID DEFAULT gen_random_uuid() NOT NULL,
    name                  VARCHAR(30)                    NOT NULL,

    CONSTRAINT pk_delivery_country PRIMARY KEY (id)
);

CREATE TABLE delivery_city
(
    id                    UUID DEFAULT gen_random_uuid() NOT NULL,
    country_id            UUID                           NOT NULL,
    name                  VARCHAR(30)                    NOT NULL,

    CONSTRAINT pk_delivery_city PRIMARY KEY (id)
);

CREATE TABLE delivery_street
(
    id                    UUID DEFAULT gen_random_uuid() NOT NULL,
    name                  VARCHAR(30)                    NOT NULL,

    CONSTRAINT pk_delivery_street PRIMARY KEY (id)
);

CREATE TABLE delivery_address
(
    id                    UUID DEFAULT gen_random_uuid() NOT NULL,
    city_id               UUID                           NOT NULL,
    street_id             UUID                           NOT NULL,
    house                 VARCHAR(5)                     NOT NULL,
    building              VARCHAR(5)                             ,
    flat                  VARCHAR(5)                             ,

    CONSTRAINT pk_delivery_address PRIMARY KEY (id)
);

CREATE TABLE delivery_order
(
    id                    UUID DEFAULT gen_random_uuid() NOT NULL,
    delivery_type_id      UUID                           NOT NULL,
    delivery_address_id   UUID                           NOT NULL,
    user_id               UUID                           NOT NULL,
    card_id               UUID                           NOT NULL,
    opening_time          TIMESTAMP                      NOT NULL,
    lead_time             TIMESTAMP                              ,
    is_delivered          Boolean DEFAULT FALSE          NOT NULL,
    status_description    VARCHAR(500)                           ,

    CONSTRAINT pk_delivery_order PRIMARY KEY (id)
);

ALTER TABLE delivery_order
    ADD CONSTRAINT fk_delivery_order_on_delivery_type FOREIGN KEY (delivery_type_id) REFERENCES delivery_type (id);
ALTER TABLE delivery_order
    ADD CONSTRAINT fk_delivery_order_on_delivery_address FOREIGN KEY (delivery_address_id) REFERENCES delivery_address (id);

ALTER TABLE delivery_address
    ADD CONSTRAINT fk_delivery_address_on_delivery_city FOREIGN KEY (city_id) REFERENCES delivery_city (id);
ALTER TABLE delivery_address
    ADD CONSTRAINT fk_delivery_address_on_delivery_street FOREIGN KEY (street_id) REFERENCES delivery_street (id);

ALTER TABLE delivery_city
    ADD CONSTRAINT fk_delivery_city_on_delivery_country FOREIGN KEY (country_id) REFERENCES delivery_country (id);
