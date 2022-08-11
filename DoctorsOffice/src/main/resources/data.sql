-- ROLES
INSERT INTO role (ID, NAME)
VALUES (1, 'ROLE_DOCTOR');

INSERT INTO role (ID, NAME)
VALUES (2, 'ROLE_PHYSIOTHERAPIST');

INSERT INTO role (ID, NAME)
VALUES (3, 'ROLE_NURSE');

INSERT INTO role (ID, NAME)
VALUES (4, 'ROLE_PATIENT');


--USERS
INSERT INTO doctor (ID,FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USERNAME)
VALUES (1,'Ime1', 'Prezime1','$2a$10$28MUwyYgna28OIxoUnE7VOpjby0JRJUU0WQV0UZdMX5XA46XAvBCK', '+381111111','doktor@gmail.com');

INSERT INTO physiotherapist (ID,FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USERNAME)
VALUES (2,'Ime2', 'Prezime2','$2a$10$28MUwyYgna28OIxoUnE7VOpjby0JRJUU0WQV0UZdMX5XA46XAvBCK', '+382222222','fizioterapeut@gmail.com');

INSERT INTO nurse (ID,FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USERNAME)
VALUES (3,'Ime3', 'Prezime3','$2a$10$28MUwyYgna28OIxoUnE7VOpjby0JRJUU0WQV0UZdMX5XA46XAvBCK', '+383333333','medicinskasestra@gmail.com');

INSERT INTO patient (ID,FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USERNAME)
VALUES (4,'Ime4', 'Prezime4','$2a$10$28MUwyYgna28OIxoUnE7VOpjby0JRJUU0WQV0UZdMX5XA46XAvBCK', '+384444444','pacijent@gmail.com');


--USER_ROLE
INSERT INTO user_role(ROLE_ID, USER_ID)
VALUES (1,1);

INSERT INTO user_role(ROLE_ID, USER_ID)
VALUES (2,2);

INSERT INTO user_role(ROLE_ID, USER_ID)
VALUES (3,3);

INSERT INTO user_role(ROLE_ID, USER_ID)
VALUES (4,4);