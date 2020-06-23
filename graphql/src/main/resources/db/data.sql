INSERT INTO person
VALUES (1, 'TEACHER', 'Jean-Michel', 'History', 'jean-michel.history@yopmail.com');
INSERT INTO person
VALUES (2, 'TEACHER', 'Jean-Michel', 'Philosophy', 'jean-michel.philosphy@yopmail.com');
INSERT INTO person
VALUES (3, 'TEACHER', 'Jean-Michel', 'Mathematics', 'jean-michel.mathematics@yopmail.com');
INSERT INTO person
VALUES (4, 'TEACHER', 'Jean-Michel', 'English', 'jean-michel.english@yopmail.com');

INSERT INTO person
VALUES (5, 'STUDENT', 'Joe', 'Chip', 'joe.chip@yopmail.com');
INSERT INTO person
VALUES (6, 'STUDENT', 'Arthur', 'Dent', 'arthur.dent@yopmail.com');
INSERT INTO person
VALUES (7, 'STUDENT', 'Hari', 'Seldon', 'hari.seldon@yopmail.com');

INSERT INTO lesson
VALUES (1, 'Introduction to history', 'HISTORY', 1, parsedatetime('16-09-2019 10:00:00', 'dd-MM-yyyy hh:mm:ss'),
        '2:00');

INSERT INTO lesson
VALUES (2, 'Mathematics level 1', 'MATHEMATICS', 3, parsedatetime('16-09-2019 15:00:00', 'dd-MM-yyyy hh:mm:ss'),
        '2:30');
INSERT INTO lesson
VALUES (3, 'Mathematics level 2', 'MATHEMATICS', 3, parsedatetime('23-09-2019 15:00:00', 'dd-MM-yyyy hh:mm:ss'),
        '2:30');
INSERT INTO lesson
VALUES (4, 'Mathematics level 3', 'MATHEMATICS', 3, parsedatetime('30-09-2019 10:00:00', 'dd-MM-yyyy hh:mm:ss'),
        '2:30');

INSERT INTO lesson
VALUES (5, 'English for newbie', 'ENGLISH', 4, parsedatetime('30-09-2019 8:00:00', 'dd-MM-yyyy hh:mm:ss'), '3:00');

INSERT INTO registration
VALUES (1, 1, 7, parsedatetime('15-08-2019 12:00:00', 'dd-MM-yyyy hh:mm:ss'), null);
INSERT INTO registration
VALUES (2, 4, 6, parsedatetime('20-08-2019 14:00:00', 'dd-MM-yyyy hh:mm:ss'), null);
INSERT INTO registration
VALUES (3, 5, 6, parsedatetime('20-08-2019 16:00:00', 'dd-MM-yyyy hh:mm:ss'), null);
