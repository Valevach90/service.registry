CREATE TABLE delivery_type
(
    id                    UUID DEFAULT gen_random_uuid() NOT NULL,
    type_name             VARCHAR(30)                    NOT NULL,

    CONSTRAINT pk_delivery_type PRIMARY KEY (id)
);

INSERT INTO delivery_type (type_name)
VALUES ('COURIER'),
       ('BANK_BRANCH');

CREATE TABLE delivery_address
(
    id                    UUID DEFAULT gen_random_uuid() NOT NULL,
    city                  VARCHAR(45)                    NOT NULL,
    street                VARCHAR(45)                    NOT NULL,
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
    status_description    VARCHAR(500)                   NOT NULL,

    CONSTRAINT pk_delivery_order PRIMARY KEY (id)
);

ALTER TABLE delivery_order
    ADD CONSTRAINT fk_delivery_order_on_delivery_type FOREIGN KEY (delivery_type_id) REFERENCES delivery_type (id);
ALTER TABLE delivery_order
    ADD CONSTRAINT fk_delivery_order_on_delivery_address FOREIGN KEY (delivery_address_id) REFERENCES delivery_address (id);
