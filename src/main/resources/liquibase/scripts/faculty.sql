-- liquibase formatted sql

-- changeset garry:1
CREATE TABLE faculty(
    id      BIGSERIAL  NOT NULL PRIMARY KEY,
    color   VARCHAR(255),
    name    VARCHAR(255)
);

-- changeset garry:2
ALTER TABLE faculty ADD UNIQUE (name);