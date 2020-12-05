DROP TABLE IF EXISTS Document;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Office;
DROP TABLE IF EXISTS Organization;
DROP TABLE IF EXISTS Country;
DROP TABLE IF EXISTS Doc_type;


CREATE TABLE IF NOT EXISTS Doc_type (
    version    INTEGER            NOT NULL COMMENT 'Служебное поле hibernate',
    code       INTEGER     UNIQUE NOT NULL COMMENT 'Код',
    name       VARCHAR(50) UNIQUE NOT NULL COMMENT 'Документ'
);
COMMENT ON TABLE Doc_type IS 'Справочник Код документа';

CREATE TABLE IF NOT EXISTS Country (
    version    INTEGER            NOT NULL COMMENT 'Служебное поле hibernate',
    code       INTEGER     UNIQUE NOT NULL COMMENT 'Код',
    name       VARCHAR(50) UNIQUE NOT NULL COMMENT 'Страна'
);
COMMENT ON TABLE Country IS 'Справочник Гражданство';

CREATE TABLE IF NOT EXISTS Document (
    id               INTEGER                     COMMENT 'Уникальный идентификатор',
    version          INTEGER            NOT NULL COMMENT 'Служебное поле hibernate',
    doc_code         INTEGER                     COMMENT 'Идентификатор справочника документа',
    FOREIGN KEY (doc_code) REFERENCES Doc_type (code),
    doc_number       VARCHAR(20) UNIQUE          COMMENT 'Номер документа',
    doc_date         VARCHAR(20)                 COMMENT 'Дата документа'
);
COMMENT ON TABLE Document IS 'Документ';
CREATE INDEX IX_document_doccode ON Document (doc_code);

CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER            NOT NULL COMMENT 'Служебное поле hibernate',
    name       VARCHAR(20) UNIQUE NOT NULL COMMENT 'Имя',
    full_name  VARCHAR(50)        NOT NULL COMMENT 'Полное имя',
    inn        VARCHAR(10)        NOT NULL COMMENT 'ИНН',
    kpp        VARCHAR(9)         NOT NULL COMMENT 'КПП',
    address    VARCHAR(50)        NOT NULL COMMENT 'Адрес',
    phone      VARCHAR(11)                 COMMENT 'Телефон',
    is_active  BOOLEAN       DEFAULT TRUE  COMMENT 'Активность'
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_organization_name ON Organization (name);

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name       VARCHAR(50)          COMMENT 'Имя',
    address    VARCHAR(50)          COMMENT 'Адрес',
    phone      VARCHAR(11)          COMMENT 'Телефон',
    is_active  BOOLEAN DEFAULT TRUE COMMENT 'Активность',
    org_id     INTEGER              COMMENT 'Идентификатор организации',
    FOREIGN KEY (org_id) REFERENCES Organization (id) ON DELETE CASCADE
);
COMMENT ON TABLE Organization IS 'Офис';
CREATE INDEX IX_office_orgid ON Office (org_id);

CREATE TABLE IF NOT EXISTS User (
    id               INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    first_name       VARCHAR(30) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(30)          COMMENT 'Фамилия',
    middle_name      VARCHAR(30)          COMMENT 'Отчество',
    position         VARCHAR(20) NOT NULL COMMENT 'Должность',
    phone            VARCHAR(11)          COMMENT 'Телефон',
    citizenship_code   INTEGER            COMMENT 'Идентификатор справочника гражданства',
    FOREIGN KEY (citizenship_code) REFERENCES Country (code),
    is_identified    BOOLEAN DEFAULT TRUE COMMENT 'Идентифицирован',
    office_id        INTEGER              COMMENT 'Идентификатор офиса',
    FOREIGN KEY (office_id) REFERENCES Office (id) ON DELETE CASCADE
);
COMMENT ON TABLE User IS 'Сотрудник';
CREATE INDEX IX_user_citizenshipcode ON User (citizenship_code);
CREATE INDEX IX_user_officeid ON User (office_id);

CREATE UNIQUE INDEX UX_Document_id ON Document (id);
ALTER TABLE Document ADD FOREIGN KEY (id) REFERENCES User(id);
