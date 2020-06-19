DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS lesson;
DROP TABLE IF EXISTS registration;

CREATE TABLE person
(
    id          INTEGER IDENTITY PRIMARY KEY,
    person_type VARCHAR(10) NOT NULL,
    first_name  VARCHAR(30) NOT NULL,
    last_name   VARCHAR(30) NOT NULL
);

CREATE TABLE lesson
(
    id         INTEGER IDENTITY PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    subject    VARCHAR(15) NOT NULL,
    teacher_id INTEGER     NOT NULL,
    start_at   DATETIME    NOT NULL,
    duration   INTERVAL HOUR TO MINUTE NOT NULL
);
ALTER TABLE lesson
    ADD CONSTRAINT fk_lesson_teacher FOREIGN KEY (teacher_id) REFERENCES person (id);

CREATE TABLE registration
(
    id            INTEGER IDENTITY PRIMARY KEY,
    lesson_id     INTEGER  NOT NULL,
    student_id    INTEGER  NOT NULL,
    registered_at DATETIME NOT NULL,
    grade         VARCHAR(1)
);
ALTER TABLE registration
    ADD CONSTRAINT fk_registration_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);
ALTER TABLE registration
    ADD CONSTRAINT fk_registration_student FOREIGN KEY (student_id) REFERENCES person (id);
ALTER TABLE registration
    ADD UNIQUE (lesson_id, student_id);
