-- liquibase formatted sql

-- changeset ihlopachev:1
CREATE TABLE AnimalReport
(
    idAnimal            BIGSERIAL PRIMARY KEY,
    photo               TEXT,
    animal_diet         TEXT,
    general_information TEXT,
    change_in_behavior  TEXT
);
CREATE TABLE CommandsForGreeting
(
    id                    BIGSERIAL PRIMARY KEY,
    commands_for_greeting TEXT
);
CREATE TABLE ReportCommands
(
    id              BIGSERIAL PRIMARY KEY,
    report_commands TEXT
);
CREATE TABLE ShelterAnimalInformation
(
    id_animal      BIGSERIAL PRIMARY KEY,
    name_animal    TEXT NOT NULL,
    kind_of_animal TEXT NOT NULL,
    adult          BIGINT,
    breed          TEXT,
    shelter_id     TEXT NOT NULL
);
CREATE TABLE ShelterInfo
(
    id_shelter          BIGSERIAL PRIMARY KEY,
    name_of_the_shelter TEXT NOT NULL,
    address             TEXT NOT NULL,
    kind_of_animal      TEXT NOT NULL,
    opening_hours       TEXT NOT NULL,
    contact_info        TEXT NOT NULL
);
CREATE TABLE UserInfo
(
    id_user          BIGSERIAL PRIMARY KEY,
    full_name        TEXT   NOT NULL,
    adult            BIGINT,
    passport_details TEXT   NOT NULL,
    adress           TEXT   NOT NULL,
    phone_number     TEXT   NOT NULL,
    user_statusTEXT  BIGINT NOT NULL,
    id_animal        BIGINT,
    trial_period     TEXT
);
CREATE TABLE VolunteerInfo
(
    id_volunteer    BIGSERIAL PRIMARY KEY,
    full_name       TEXT NOT NULL,
    adress_shelter  TEXT NOT NULL,
    contact_details TEXT NOT NULL,
    opening_hours   TEXT NOT NULL
);
CREATE TABLE CommandsForConsultation
(
    id_volunteer              BIGSERIAL PRIMARY KEY,
    commands_for_consultation TEXT
);

-- changeset nikdenalb:2
CREATE TABLE Bot0message
(
    message_type BIGSERIAL PRIMARY KEY,
    message_text TEXT
);