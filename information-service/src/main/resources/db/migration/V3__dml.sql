-- country

INSERT INTO country (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_name)
VALUES (1, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 'Germany');

INSERT INTO country (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_name)
VALUES (2, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 'France');

INSERT INTO country (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_name)
VALUES (3, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 'Litauen');

-- city


INSERT INTO city (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_id, city_name)
VALUES (1, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 1, 'Berlin');

INSERT INTO city (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_id, city_name)
VALUES (2, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 1, 'Hamburg');

INSERT INTO city (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_id, city_name)
VALUES (3, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 1, 'Dresden');

INSERT INTO city (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_id, city_name)
VALUES (4, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 2, 'Paris');

INSERT INTO city (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_id, city_name)
VALUES (5, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 3, 'Vilnius');

INSERT INTO city (id, created_by, created_date, deleted, last_modified_by, last_modified_date, country_id, city_name)
VALUES (6, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 3, 'Kaunas');

-- address

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (1, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 1, 'Unter den Linden', '13/15');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (2, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 1, 'Pariser Platz', '3');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (3, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 1, 'Frankfurter Allee', '69');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (4, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 2, 'Speersort', '10');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (5, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 3, 'Leipziger', '159');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (6, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 3, 'Altmarkt', '7');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (7, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 4, 'Rue Lepic', '27');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (8, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 4, 'Rue Pierre Demours', '31');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (9, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 5, 'Shvitrigailos', '11-D');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (10, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 5, 'Algirdo', '24');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (11, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 5, 'Gedimino', '12');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (12, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 6, 'Maironio', '25');

INSERT INTO address (id, created_by, created_date, deleted, last_modified_by, last_modified_date, city_id, street_name, building_number)
VALUES (13, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 6, 'Nemuno', '3');

-- time_table

INSERT INTO time_table (id, created_by, created_date, deleted, last_modified_by, last_modified_date, address_id,
                        week_day_from, week_day_to, time_from, time_to)
VALUES (1, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 1,
        'Mon', 'Fri', '9:00', '18:00');

INSERT INTO time_table (id, created_by, created_date, deleted, last_modified_by, last_modified_date, address_id,
                        week_day_from, week_day_to, time_from, time_to)
VALUES (2, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 1,
        'Sat', 'Sun', '11:00', '16:00');

INSERT INTO time_table (id, created_by, created_date, deleted, last_modified_by, last_modified_date, address_id,
                        week_day_from, week_day_to, time_from, time_to)
VALUES (3, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 2,
        'Mon', 'Fri', '9:00', '18:00');

INSERT INTO time_table (id, created_by, created_date, deleted, last_modified_by, last_modified_date, address_id,
                        week_day_from, week_day_to, time_from, time_to)
VALUES (4, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 2,
        'Sat', 'Sun', '11:00', '16:00');

INSERT INTO time_table (id, created_by, created_date, deleted, last_modified_by, last_modified_date, address_id,
                        week_day_from, week_day_to, time_from, time_to)
VALUES (5, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 3,
        'Mon', 'Fri', '9:00', '18:00');

INSERT INTO time_table (id, created_by, created_date, deleted, last_modified_by, last_modified_date, address_id,
                        week_day_from, week_day_to, time_from, time_to)
VALUES (6, 'ADMIN', '2017-08-19 12:17:55', false, 'ADMIN', '2017-08-19 12:17:55', 3,
        'Sat', 'Sun', '11:00', '16:00');



