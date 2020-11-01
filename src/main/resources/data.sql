DELETE FROM User;
DELETE FROM Document;
DELETE FROM Country;
DELETE FROM Doc;
DELETE FROM Office;
DELETE FROM Organization;

INSERT INTO Doc (code, name) VALUES (21, 'Паспорт гражданина Российской Федерации');
INSERT INTO Doc (code, name) VALUES (12, 'Вид на жительство в Российской Федерации');

INSERT INTO Country (code, name) VALUES (643, 'Российская Федерация');
INSERT INTO Country (code, name) VALUES (578, 'Королевство Норвегия');

INSERT INTO Organization (name, full_name, inn, kpp, address, phone, is_active)
    VALUES ('Вектор', 'ООО Вектор', 9977001234, 987654321, 'ул.Мира, 24', 84951112233, true);

INSERT INTO Office (name, address, phone, is_active, org_id)
    VALUES ('БЦ Океан', 'ул.Мира, 24', 84951112233, true, 1);
INSERT INTO Office (name, address, phone, is_active, org_id)
    VALUES ('БЦ Полянка', 'ул.Правды, 3', 84958889900, true, 1);

INSERT INTO Document (doc_type, doc_number, doc_date, citizenship_code, user_id)
VALUES (12, 4365364, '2025-12-31', 578, 1);
INSERT INTO Document (doc_type, doc_number, doc_date, citizenship_code, user_id)
VALUES (12, 7554432, '2026-11-30', 578, 2);

INSERT INTO User (first_name, second_name, middle_name, position, phone, document_id, is_identified, office_id)
    VALUES ('John', 'Doe', null, 'manager', 89990001122, 1, true, 2);
INSERT INTO User (first_name, second_name, middle_name, position, phone, document_id, is_identified, office_id)
    VALUES ('Mary', 'Public', null, 'hr', 89990002222, 2, true, 1);




