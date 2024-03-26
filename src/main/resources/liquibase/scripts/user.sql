-- liquibase formatted sql

-- changeset ihlopachev:1
DROP TYPE IF EXISTS type_pet;
DROP TYPE IF EXISTS type_user;
CREATE TYPE type_pet AS enum ('cat', 'dog');
CREATE TYPE type_user AS enum ('user', 'volunteer');
CREATE TABLE IF NOT EXISTS reports
(
    id                  BIGSERIAL PRIMARY KEY,
    photo               VARCHAR[255],
    diet                TEXT,
    change_in_behavior  TEXT,
    general_information TEXT
);
CREATE TABLE IF NOT EXISTS pets
(
    id       BIGSERIAL  PRIMARY KEY,
    name_pet TEXT       NOT NULL,
    pet_type type_pet   NOT NULL,
    adult    BIGINT     NOT NULL,
    breed    TEXT       NOT NULL
);
CREATE TABLE IF NOT EXISTS shelters
(
    id                  BIGSERIAL PRIMARY KEY,
    name_of_the_shelter TEXT    NOT NULL,
    animal_type         BIGINT  NOT NULL,
    address             TEXT    NOT NULL,
    opening_hours       TEXT    NOT NULL,
    contact_info        TEXT    NOT NULL
);
CREATE TABLE IF NOT EXISTS users
(
    id           BIGSERIAL PRIMARY KEY,
    chat_id      BIGINT    NOT NULL,
    user_type    type_user NOT NULL,
    full_name    TEXT,
    adult        BIGINT,
    address      TEXT,
    phone_number TEXT,
    is_adopted   BIGINT
);