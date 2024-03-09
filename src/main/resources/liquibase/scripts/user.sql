-- liquibase formatted sql

-- changeset ihlopachev:1
CREATE TABLE cat_report
(
    id                  BIGSERIAL PRIMARY KEY,
    photo               TEXT,
    cat_diet         TEXT,
    change_in_behavior  TEXT,
    general_information TEXT
);
CREATE TABLE cats
(
    id         BIGSERIAL PRIMARY KEY,
    name_cat   TEXT   NOT NULL,
    adult      BIGINT NOT NULL,
    breed      TEXT NOT NULL
);
CREATE TABLE shelters_cat
(
    id                  BIGSERIAL PRIMARY KEY,
    name_of_the_shelter TEXT NOT NULL,
    address             TEXT NOT NULL,
    opening_hours       TEXT NOT NULL,
    contact_info        TEXT NOT NULL
);
CREATE TABLE users_cat
(
    id           BIGSERIAL PRIMARY KEY,
    chat_id      BIGINT NOT NULL ,
    full_name    TEXT NOT NULL,
    adult        BIGINT,
    address      TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    isAdopted    BOOLEAN DEFAULT false
);
CREATE TABLE dog_report
(
    id                  BIGSERIAL PRIMARY KEY,
    photo               TEXT,
    dog_diet         TEXT,
    change_in_behavior  TEXT,
    general_information TEXT
);
CREATE TABLE dogs
(
    id         BIGSERIAL PRIMARY KEY,
    name_dog   TEXT   NOT NULL,
    adult      BIGINT NOT NULL,
    breed      TEXT NOT NULL
);
CREATE TABLE shelters_dog
(
    id                  BIGSERIAL PRIMARY KEY,
    name_of_the_shelter TEXT NOT NULL,
    address             TEXT NOT NULL,
    opening_hours       TEXT NOT NULL,
    contact_info        TEXT NOT NULL
);
CREATE TABLE users_dog
(
    id           BIGSERIAL PRIMARY KEY,
    chat_id      BIGINT NOT NULL ,
    full_name    TEXT NOT NULL,
    adult        BIGINT,
    address      TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    isAdopted    BOOLEAN DEFAULT false
);
-- changeset nikdenalb:2
CREATE TABLE Bot0messages
(
    message_type TEXT PRIMARY KEY,
    message_text TEXT
);