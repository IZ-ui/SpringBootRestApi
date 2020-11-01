DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Document;
DROP TABLE IF EXISTS Country;
DROP TABLE IF EXISTS Doc;
DROP TABLE IF EXISTS Office;
DROP TABLE IF EXISTS Organization;

CREATE TABLE Organization (
    id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    name       VARCHAR(20) NOT NULL COMMENT 'Имя',
    full_name  VARCHAR(50) NOT NULL COMMENT 'Полное имя',
    inn        VARCHAR(10) NOT NULL COMMENT 'ИНН',
    kpp        VARCHAR(9)  NOT NULL COMMENT 'КПП',
    address    VARCHAR(50) NOT NULL COMMENT 'Адрес',
    phone      VARCHAR(11) NOT NULL COMMENT 'Телефон',
    is_active  BOOLEAN     NOT NULL COMMENT 'Активен'
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_Organization_Name ON Organization (name);

CREATE TABLE Office (
    id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    name       VARCHAR(20) NOT NULL COMMENT 'Имя',
    address    VARCHAR(50) NOT NULL COMMENT 'Адрес',
    phone      VARCHAR(11) NOT NULL COMMENT 'Телефон',
    is_active  BOOLEAN     NOT NULL COMMENT 'Активен',
    org_id     INTEGER              COMMENT 'Идентификатор организации',
    FOREIGN KEY (org_id) REFERENCES Organization (id) ON DELETE CASCADE
);
COMMENT ON TABLE Organization IS 'Офис';
CREATE INDEX IX_Office_ID_OrgId ON Office (id, org_id);

CREATE TABLE Country (
    code  INTEGER     NOT NULL COMMENT 'Код страны' PRIMARY KEY,
    name  VARCHAR(50) NOT NULL COMMENT 'Страна'
);
COMMENT ON TABLE Country IS 'Страна';

CREATE TABLE Doc (
    code       INTEGER     NOT NULL COMMENT 'Код' PRIMARY KEY,
    name       VARCHAR(50) NOT NULL COMMENT 'Документ'
);
COMMENT ON TABLE Doc IS 'Код документа';

CREATE TABLE Document (
    id               INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    doc_type         INTEGER     NOT NULL COMMENT 'Код документа',
    FOREIGN KEY (doc_type) REFERENCES Doc (code),
    doc_number       VARCHAR(20) NOT NULL COMMENT 'Номер документа',
    doc_date         DATE        NOT NULL COMMENT 'Дата документа',
    citizenship_code INTEGER     NOT NULL COMMENT 'Код гражданства',
    FOREIGN KEY (citizenship_code) REFERENCES Country (code),
    user_id          INTEGER              COMMENT 'Идентификатор организации',
--     FOREIGN KEY (user_id) REFERENCES User (id) ON DELETE CASCADE,
    CONSTRAINT UX_Document_ID_UserId UNIQUE (id, user_id)
);
COMMENT ON TABLE Document IS 'Документ';

CREATE TABLE User (
    id               INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    first_name       VARCHAR(20) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(20) NOT NULL COMMENT 'Фамилия',
    middle_name      VARCHAR(20)          COMMENT 'Отчество',
    position         VARCHAR(20) NOT NULL COMMENT 'Должность',
    phone            VARCHAR(11) NOT NULL COMMENT 'Телефон',
    document_id      INTEGER     NOT NULL COMMENT 'Идентификатор документа',
    FOREIGN KEY (document_id) REFERENCES Document (id),
    is_identified    BOOLEAN     NOT NULL COMMENT 'Идентифицирован',
    office_id        INTEGER     NOT NULL COMMENT 'Идентификатор офиса',
    FOREIGN KEY (office_id) REFERENCES Office (id) ON DELETE CASCADE
);
COMMENT ON TABLE User IS 'Сотрудник';
CREATE INDEX IX_User_Id_OfficeId ON User (id, office_id);

