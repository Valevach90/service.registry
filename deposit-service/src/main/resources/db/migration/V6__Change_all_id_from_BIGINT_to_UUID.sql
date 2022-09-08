ALTER TABLE deposit_types
    DROP COLUMN id CASCADE,
    ADD COLUMN id UUID UNIQUE DEFAULT gen_random_uuid() NOT NULL,
    ADD CONSTRAINT pk_deposit_types PRIMARY KEY (id);

ALTER TABLE currencies
    DROP COLUMN id CASCADE,
    ADD COLUMN id UUID UNIQUE DEFAULT gen_random_uuid() NOT NULL,
    ADD CONSTRAINT pk_currencies PRIMARY KEY (id);

ALTER TABLE deposit_products
    DROP COLUMN id CASCADE ,
    ADD COLUMN id UUID UNIQUE DEFAULT gen_random_uuid() NOT NULL,
    ADD CONSTRAINT pk_deposits_products PRIMARY KEY (id),

    DROP COLUMN deposit_type_id CASCADE ,
    ADD COLUMN deposit_type_id UUID NOT NULL,

    DROP COLUMN currency_id CASCADE ,
    ADD COLUMN currency_id UUID NOT NULL;

ALTER TABLE deposits
    DROP COLUMN id CASCADE ,
    ADD COLUMN id UUID UNIQUE DEFAULT gen_random_uuid() NOT NULL,
    ADD CONSTRAINT pk_deposits PRIMARY KEY (id),

    DROP COLUMN deposit_product_id CASCADE ,
    ADD COLUMN deposit_product_id UUID NOT NULL,

    DROP COLUMN deposit_type_id CASCADE ,
    ADD COLUMN deposit_type_id UUID NOT NULL,

    DROP COLUMN currency_id CASCADE,
    ADD COLUMN currency_id UUID NOT NULL,

    DROP COLUMN user_id CASCADE,
    ADD COLUMN user_id UUID NOT NULL;

ALTER TABLE transfers
    DROP COLUMN user_id CASCADE ,
    ADD COLUMN user_id UUID NOT NULL,

    DROP COLUMN deposit_id CASCADE ,
    ADD COLUMN deposit_id UUID NOT NULL;

ALTER TABLE deposit_products
    ADD CONSTRAINT fk_deposit_products_on_deposit_types FOREIGN KEY (deposit_type_id) REFERENCES deposit_types (id),
    ADD CONSTRAINT fk_deposit_products_on_currencies FOREIGN KEY (currency_id) REFERENCES currencies (id);

ALTER TABLE deposits
    ADD CONSTRAINT fk_deposits_on_deposit_products FOREIGN KEY (deposit_product_id) REFERENCES deposit_products (id),
    ADD CONSTRAINT fk_deposits_on_deposit_types FOREIGN KEY (deposit_type_id) REFERENCES deposit_types (id),
    ADD CONSTRAINT fk_deposits_on_currencies FOREIGN KEY (currency_id) REFERENCES currencies (id);

ALTER TABLE transfers
    ADD CONSTRAINT fk_transfer_on_deposit FOREIGN KEY (deposit_id) REFERENCES deposits (id) ON UPDATE CASCADE;





