DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Document;
DROP TABLE IF EXISTS Country;
DROP TABLE IF EXISTS Doc;
DROP TABLE IF EXISTS Office;
DROP TABLE IF EXISTS Organization;

CREATE TABLE Doc (
    id         INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER            NOT NULL COMMENT 'Служебное поле hibernate',
    code       INTEGER UNIQUE     NOT NULL COMMENT 'Код',
    name       VARCHAR(50) UNIQUE NOT NULL COMMENT 'Документ'
);
COMMENT ON TABLE Doc IS 'Справочник Код документа';

CREATE TABLE Country (
    id         INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER            NOT NULL COMMENT 'Служебное поле hibernate',
    code       INTEGER UNIQUE     NOT NULL COMMENT 'Код',
    name       VARCHAR(50) UNIQUE NOT NULL COMMENT 'Страна'
);
COMMENT ON TABLE Country IS 'Справочник Гражданство';

CREATE TABLE Document (
    id               INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER            NOT NULL COMMENT 'Служебное поле hibernate',
    doc_id           INTEGER            NOT NULL COMMENT 'Идентификатор справочника документа',
    FOREIGN KEY (doc_id) REFERENCES Doc (id),
    doc_number       VARCHAR(20) UNIQUE NOT NULL COMMENT 'Номер документа',
    doc_date         DATE               NOT NULL COMMENT 'Дата документа'
);
COMMENT ON TABLE Document IS 'Документ';
CREATE INDEX IX_document_docid ON Document (doc_id);

CREATE TABLE Organization (
    id         INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER            NOT NULL COMMENT 'Служебное поле hibernate',
    name       VARCHAR(20) UNIQUE NOT NULL COMMENT 'Имя',
    full_name  VARCHAR(50)        NOT NULL COMMENT 'Полное имя',
    inn        VARCHAR(10)        NOT NULL COMMENT 'ИНН',
    kpp        VARCHAR(9)         NOT NULL COMMENT 'КПП',
    address    VARCHAR(50)        NOT NULL COMMENT 'Адрес',
    phone      VARCHAR(11)                 COMMENT 'Телефон',
    is_active  BOOLEAN            NOT NULL COMMENT 'Активность'
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_organization_name ON Organization (name);

CREATE TABLE Office (
    id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name       VARCHAR(50) NOT NULL COMMENT 'Имя',
    address    VARCHAR(50) NOT NULL COMMENT 'Адрес',
    phone      VARCHAR(11) NOT NULL COMMENT 'Телефон',
    is_active  BOOLEAN     NOT NULL COMMENT 'Активность',
    org_id     INTEGER              COMMENT 'Идентификатор организации',
    FOREIGN KEY (org_id) REFERENCES Organization (id) ON DELETE CASCADE
);
COMMENT ON TABLE Organization IS 'Офис';
CREATE INDEX IX_office_orgid ON Office (org_id);

CREATE TABLE User (
    id               INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    first_name       VARCHAR(30) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(30)          COMMENT 'Фамилия',
    middle_name      VARCHAR(30)          COMMENT 'Отчество',
    position         VARCHAR(20) NOT NULL COMMENT 'Должность',
    phone            VARCHAR(11)          COMMENT 'Телефон',
    citizenship_id   INTEGER     NOT NULL COMMENT 'Идентификатор справочника гражданства',
    FOREIGN KEY (citizenship_id) REFERENCES Country (id),
    is_identified    BOOLEAN     NOT NULL COMMENT 'Идентифицирован',
    office_id        INTEGER     NOT NULL COMMENT 'Идентификатор офиса',
    FOREIGN KEY (office_id) REFERENCES Office (id) ON DELETE CASCADE
);
COMMENT ON TABLE User IS 'Сотрудник';
CREATE INDEX IX_user_documentid ON User (citizenship_id);
CREATE INDEX IX_user_officeid ON User (office_id);

