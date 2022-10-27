SELECT setval('information_service.city_id_seq', COALESCE((SELECT MAX(id)+1 FROM information_service.city), 1), false);
SELECT setval('information_service.street_id_seq', COALESCE((SELECT MAX(id)+1 FROM information_service.street), 1), false);
SELECT setval('information_service.time_table_id_seq', COALESCE((SELECT MAX(id)+1 FROM information_service.time_table), 1), false);
SELECT setval('information_service.file_info_id_seq', COALESCE((SELECT MAX(id)+1 FROM information_service.file_info), 1), false);
SELECT setval('information_service.bank_branch_id_seq', COALESCE((SELECT MAX(id)+1 FROM information_service.bank_branch), 1), false);