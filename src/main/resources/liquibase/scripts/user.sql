-- liquibase formatted sql

-- changeset ihlopachev:1
CREATE TABLE reports
(
    id                  BIGSERIAL PRIMARY KEY,
    photo               TEXT,
    diet                TEXT,
    change_in_behavior  TEXT,
    general_information TEXT
);
CREATE TABLE pets
(
    id       BIGSERIAL PRIMARY KEY,
    name_Pet TEXT   NOT NULL,
    type_Pet TEXT   NOT NULL,
    adult    BIGINT NOT NULL,
    breed    TEXT   NOT NULL
);
CREATE TABLE shelters
(
    id                  BIGSERIAL PRIMARY KEY,
    name_of_the_shelter TEXT NOT NULL,
    address             TEXT NOT NULL,
    opening_hours       TEXT NOT NULL,
    contact_info        TEXT NOT NULL
);
CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    chat_id      BIGINT NOT NULL,
    full_name    TEXT  ,
    adult        BIGINT,
    address      TEXT   ,
    phone_number TEXT   ,
    is_adopted   BIGINT 
);
-- changeset nikdenalb:2
CREATE TABLE Bot0messages
(
    message_type TEXT PRIMARY KEY,
    message_text TEXT
);