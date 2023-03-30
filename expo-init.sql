DROP SCHEMA IF EXISTS expo CASCADE; -- TODO. NB: Sletter all data til prosjektet!
CREATE SCHEMA expo;
SET SEARCH_PATH TO expo;

CREATE TABLE event
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    event_start TIMESTAMP    NOT NULL,
    event_end   TIMESTAMP    NOT NULL
);

CREATE TABLE access_level
(
    id          SERIAL PRIMARY KEY,
    description VARCHAR(20) NOT NULL
);

CREATE TABLE "user" -- user is a reserved word in postgres
(
    username     VARCHAR(20) PRIMARY KEY,
    phone        VARCHAR(15),
    email        VARCHAR(100),
    hash         VARCHAR(255) NOT NULL,
    salt         VARCHAR(255) NOT NULL,
    access_level INTEGER      REFERENCES access_level (id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE user_event
(
    id_event INTEGER REFERENCES event (id) ON DELETE CASCADE            NOT NULL,
    username VARCHAR(20) REFERENCES "user" (username) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
    PRIMARY KEY (id_event, username)
);

CREATE TABLE stand
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255)                                    NOT NULL,
    description TEXT                                            NOT NULL,
    image       VARCHAR(255), -- Link to image
    url         VARCHAR(255), -- Link to website
    event       INTEGER REFERENCES event (id) ON DELETE CASCADE NOT NULL,
    responsible VARCHAR(20)                                     REFERENCES "user" (username) ON DELETE SET NULL,
    UNIQUE (title, event)
);

CREATE TABLE voter
(
    id       CHAR(32) PRIMARY KEY,
    id_event INTEGER REFERENCES event (id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE vote
(
    id_voter CHAR(32) REFERENCES voter (id) ON DELETE CASCADE NOT NULL,
    id_stand INTEGER REFERENCES stand (id) ON DELETE CASCADE  NOT NULL,
    stars    INTEGER CHECK ( stars >= 0 AND stars <= 5 )      NOT NULL,
    PRIMARY KEY (id_voter, id_stand)
);

CREATE TABLE exhibitor
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname  VARCHAR(50) NOT NULL,
    stand     INTEGER     REFERENCES stand (id) ON DELETE SET NULL,
    phone     VARCHAR(15) NOT NULL
);

CREATE VIEW expo.total_votes AS
(
SELECT s.id, s.title, SUM(stars) AS total_stars
FROM expo.vote v,
     expo.stand s
WHERE v.id_stand = s.id
GROUP BY s.id
ORDER BY s.id
    );
