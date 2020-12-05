INSERT INTO Doc_type (version, code, name) VALUES (0, 21, 'Паспорт гражданина Российской Федерации');
INSERT INTO Doc_type (version, code, name) VALUES (0, 12, 'Вид на жительство в Российской Федерации');

INSERT INTO Country (version, code, name) VALUES (0, 643, 'Российская Федерация');
INSERT INTO Country (version, code, name) VALUES (0, 578, 'Королевство Норвегия');

INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active)
    VALUES (0, 'Альфа', 'ООО Альфа', 9977001234, 987654321, 'ул.Мира, 24', 84951112233, true);
INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active)
    VALUES (0, 'Бета', 'ООО Бета', 9977004444, 987651111, 'ул.Кирова, 1', 84952223344, true);
INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 'Zed', 'LLC Zed', 9977004444, 987651111, 'Baker str.5', 84952228888, true);

INSERT INTO Office (version, name, address, phone, is_active, org_id)
    VALUES (0, 'Офис1 Альфа', 'ул.Мира, 24', 84951112233, true, 2);
INSERT INTO Office (version, name, address, phone, is_active, org_id)
    VALUES (0, 'Офис2 Альфа', 'ул.Правды, 3', 84958889900, true, 2);
INSERT INTO Office (version, name, address, phone, is_active, org_id)
VALUES (0, 'Eng', 'Street', 84958889900, true, 3);

INSERT INTO User (version, first_name, second_name, position, phone, citizenship_code, is_identified, office_id)
VALUES (0, 'John', 'Doe', 'manager', 89990001122, 643, true, 2);
INSERT INTO User (version, first_name, second_name, position, phone, citizenship_code, is_identified, office_id)
VALUES (0, 'Mary', 'Public', 'hr', 89990002222, 643, true, 1);


INSERT INTO Document (id, version, doc_code, doc_number, doc_date)
VALUES (1, 0, 12, 4365364, '2025-12-31');
INSERT INTO Document (id, version, doc_code, doc_number, doc_date)
VALUES (2, 0, 12, 7554432, '2026-11-30');





