-- liquibase formatted sql

-- changeset garry:1
CREATE TABLE student (
    id          BIGINT NOT NULL PRIMARY KEY,
    age         INTEGER NOT NULL ,
    name        VARCHAR(255),
    faculty_id  BIGINT REFERENCES faculty
);

-- changeset garry:2
ALTER TABLE student
    ADD CONSTRAINT check_positive_age CHECK (age > 0);