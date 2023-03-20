SET SEARCH_PATH TO expo;

INSERT INTO event(name, event_start, event_end)
VALUES ('Test Expo', now(), now() + INTERVAL '31 days');

INSERT INTO access_level
VALUES (0, 'admin'),
       (1, 'jury'),
       (2, 'exhibitor');

INSERT INTO "user"
VALUES ('admin', '123445678', 'admin@admin.com', 'admin', '', 0),
       ('jury', '123445678', 'jury@jury.com', 'jury', '', 1),
       ('exhibitor1', '123445678', 'exhibitor1@exhibitor.com', 'exhibitor1', '', 2),
       ('exhibitor2', '87654321', 'exhibitor2@exhibitor.com', 'exhibitor2', '', 2);

INSERT INTO voter
VALUES ('1', 1),
       ('2', 1),
       ('3', 1);

INSERT INTO stand(title, description, image, url, event, responsible)
VALUES ('test1', 'description', null, null, 1, 'exhibitor1'),
       ('test2', 'description', null, null, 1, 'exhibitor2');

INSERT INTO exhibitor(firstname, lastname, stand, phone)
VALUES ('Kontakt', 'Person', 1, '12345678'),
       ('Person', 'Kontakt', 2, '23456789');

INSERT INTO user_event(id_event,username) VALUES
                                              (1, 'exhibitor1');