INSERT INTO information_service.country (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_name) VALUES (1, now(), current_user, now(), current_user, false, 'Germany');
INSERT INTO information_service.country (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_name) VALUES (2, now(), current_user, now(), current_user, false, 'France');
INSERT INTO information_service.country (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_name) VALUES (3, now(), current_user, now(), current_user
, false, 'Litauen');


INSERT INTO information_service.city (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_id, city_name) VALUES (1, now(), current_user, now(), current_user, false, 1, 'Berlin');
INSERT INTO information_service.city (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_id, city_name) VALUES (2, now(), current_user, now(), current_user, false, 1, 'Hamburg');
INSERT INTO information_service.city (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_id, city_name) VALUES (3, now(), current_user, now(), current_user, false, 1, 'Dresden');
INSERT INTO information_service.city (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_id, city_name) VALUES (4, now(), current_user, now(), current_user, false, 2, 'Paris');
INSERT INTO information_service.city (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_id, city_name) VALUES (5, now(), current_user, now(), current_user, false, 3, 'Vilnius');
INSERT INTO information_service.city (id, created_date, created_by, last_modified_date, last_modified_by, deleted, country_id, city_name) VALUES (6, now(), current_user, now(), current_user, false, 3, 'Kaunas');

INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (1, now(), current_user, now(), current_user, false, 1, 'Unter den Linden');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (2, now(), current_user, now(), current_user, false, 1, 'Pariser Platz');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (3, now(), current_user, now(), current_user, false, 1, 'Frankfurter Allee');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (4, now(), current_user, now(), current_user, false, 2, 'Speersort');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (5, now(), current_user, now(), current_user, false, 3, 'Leipziger');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (6, now(), current_user, now(), current_user, false, 3, 'Altmarkt');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (7, now(), current_user, now(), current_user, false, 4, 'Rue Lepic');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (8, now(), current_user, now(), current_user, false, 4, 'Rue Pierre Demours');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (9, now(), current_user, now(), current_user, false, 5, 'Shvitrigailos');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (10, now(), current_user, now(), current_user, false, 5, 'Algirdo');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (11, now(), current_user, now(), current_user, false, 5, 'Gedimino');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (12, now(), current_user, now(), current_user, false, 6, 'Maironio');
INSERT INTO information_service.street (id, created_date, created_by, last_modified_date, last_modified_by, deleted, city_id, street_name) VALUES (13, now(), current_user, now(), current_user, false, 6, 'Nemuno');


INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (1, now(), current_user, now(), current_user, false, 1, '13', '15');
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (2, now(), current_user, now(), current_user, false, 2, '3', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (3, now(), current_user, now(), current_user, false, 3, '69', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (4, now(), current_user, now(), current_user, false, 4, '10', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (5, now(), current_user, now(), current_user, false, 5, '159', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (6, now(), current_user, now(), current_user, false, 6, '7', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (7, now(), current_user, now(), current_user, false, 7, '27', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (8, now(), current_user, now(), current_user, false, 8, '31', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (9, now(), current_user, now(), current_user, false, 9, '11', 'D');
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (10, now(), current_user, now(), current_user, false, 10, '24', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (11, now(), current_user, now(), current_user, false, 11, '12', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (12, now(), current_user, now(), current_user, false, 12, '25', null);
INSERT INTO information_service.address (id, created_date, created_by, last_modified_date, last_modified_by, deleted, street_id, house, building) VALUES (13, now(), current_user, now(), current_user, false, 13, '3', null);


INSERT INTO information_service.bank_branch (id, created_date, created_by, last_modified_date, last_modified_by, deleted, address_id, branch_number, branch_coordinates, work_at_weekend, cash_withdraw, money_transfer, accept_payment, currency_exchange, exotic_currency, ramp, replenish_card, replenish_account, consultation, insurance, is_closed) VALUES (1, now(), current_user, now(), current_user, false, 1, '55443', '52°31''0"N 13°23''19"E', false, false, false, false, false, false, false, false, false, false, false, false);

INSERT INTO information_service.time_table (id, created_date, created_by, last_modified_date, last_modified_by, deleted, address_id, week_day_from, week_day_to, time_from, time_to) VALUES (1, now(), current_user, now(), current_user, false, 1, 'MON', 'FRI', '10:00', '18:00');
