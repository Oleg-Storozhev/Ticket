CREATE DATABASE IF NOT EXISTS tickets;

CREATE TABLE IF NOT EXISTS tickets(
        id serial,
        stationFrom varchar(200) NOT NULL,
        stationTo varchar(200) NOT NULL,
        departure date NOT NULL,
        arrival date NOT NULL,
        PRIMARY KEY (id)
);

INSERT INTO tickets (stationFrom, stationTo, departure, arrival)
VALUES ('Odessa', 'Kiev', '21.03.10', '21.03.11'),
       ('Odessa', 'Lviv', '21.03.10', '21.03.11'),
       ('Kiev', 'Lviv', '21.03.12', '21.03.13'),
       ('Kiev', 'Odessa', '21.03.15', '21.03.16'),
       ('Lviv', 'Odessa', '21.03.15', '21.03.16'),
       ('Kiev', 'Odessa', '21.03.19', '21.03.20');

