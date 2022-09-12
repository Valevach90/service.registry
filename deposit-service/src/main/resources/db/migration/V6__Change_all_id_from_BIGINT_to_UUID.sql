ALTER TABLE deposit_products
    DROP CONSTRAINT fk_deposit_products_on_deposit_types,
    DROP CONSTRAINT fk_deposit_products_on_currencies;

ALTER TABLE deposits
    DROP CONSTRAINT fk_deposits_on_deposit_products,
    DROP CONSTRAINT fk_deposits_on_deposit_types,
    DROP CONSTRAINT fk_deposits_on_currencies;

ALTER TABLE transfers
    DROP CONSTRAINT fk_transfer_on_deposit;

ALTER TABLE deposit_types
    DROP COLUMN id,
    ADD COLUMN id UUID UNIQUE DEFAULT gen_random_uuid() NOT NULL;

ALTER TABLE currencies
    DROP COLUMN id,
    ADD COLUMN id UUID UNIQUE DEFAULT gen_random_uuid() NOT NULL;

ALTER TABLE deposit_products
    DROP COLUMN id,
    ADD COLUMN id UUID UNIQUE DEFAULT gen_random_uuid() NOT NULL,

    DROP COLUMN deposit_type_id,
    ADD COLUMN deposit_type_id UUID NOT NULL,

    DROP COLUMN currency_id,
    ADD COLUMN currency_id UUID NOT NULL;

ALTER TABLE deposits
    DROP COLUMN id,
    ADD COLUMN id UUID UNIQUE DEFAULT gen_random_uuid() NOT NULL,

    DROP COLUMN deposit_product_id,
    ADD COLUMN deposit_product_id UUID NOT NULL,

    DROP COLUMN deposit_type_id,
    ADD COLUMN deposit_type_id UUID NOT NULL,

    DROP COLUMN currency_id,
    ADD COLUMN currency_id UUID NOT NULL,

    DROP COLUMN user_id,
    ADD COLUMN user_id UUID NOT NULL;

ALTER TABLE transfers
    DROP COLUMN user_id,
    ADD COLUMN user_id UUID NOT NULL,

    DROP COLUMN deposit_id,
    ADD COLUMN deposit_id UUID NOT NULL;

ALTER TABLE deposit_types
    ADD CONSTRAINT pk_deposit_types PRIMARY KEY (id);

ALTER TABLE currencies
    ADD CONSTRAINT pk_currencies PRIMARY KEY (id);

ALTER TABLE deposit_products
    ADD CONSTRAINT pk_deposits_products PRIMARY KEY (id);

ALTER TABLE deposits
    ADD CONSTRAINT pk_deposits PRIMARY KEY (id);

ALTER TABLE deposit_products
    ADD CONSTRAINT fk_deposit_products_on_deposit_types FOREIGN KEY (deposit_type_id) REFERENCES deposit_types (id),
    ADD CONSTRAINT fk_deposit_products_on_currencies FOREIGN KEY (currency_id) REFERENCES currencies (id);

ALTER TABLE deposits
    ADD CONSTRAINT fk_deposits_on_deposit_products FOREIGN KEY (deposit_product_id) REFERENCES deposit_products (id),
    ADD CONSTRAINT fk_deposits_on_deposit_types FOREIGN KEY (deposit_type_id) REFERENCES deposit_types (id),
    ADD CONSTRAINT fk_deposits_on_currencies FOREIGN KEY (currency_id) REFERENCES currencies (id);

ALTER TABLE transfers
    ADD CONSTRAINT fk_transfer_on_deposit FOREIGN KEY (deposit_id) REFERENCES deposits (id) ON UPDATE CASCADE;





