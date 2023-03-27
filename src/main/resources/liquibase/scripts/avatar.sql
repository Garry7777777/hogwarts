-- liquibase formatted sql

-- changeset garry:1
CREATE TABLE  avatar(
    id          BIGSERIAL  NOT NULL PRIMARY KEY,
    data        BYTEA,
    file_path   VARCHAR(255),
    file_size   BIGINT NOT NULL ,
    media_type  VARCHAR(255),
    student_id  BIGINT REFERENCES student
);