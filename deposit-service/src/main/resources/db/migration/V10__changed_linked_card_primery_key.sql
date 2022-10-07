ALTER TABLE linked_cards
    DROP CONSTRAINT pk_linked_card;

ALTER TABLE linked_cards
    ADD CONSTRAINT pk_linked_card PRIMARY KEY (id, deposit_id);