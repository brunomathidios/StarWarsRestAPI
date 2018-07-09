create database if not exists starwars;

CREATE TABLE planet (
    id_planet INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nm_planet VARCHAR(150) NOT NULL,
    number_movies INT UNSIGNED NULL,
    PRIMARY KEY (id_planet)
);

CREATE TABLE climate (
    id_climate INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nm_climate VARCHAR(50) NOT NULL,
    id_planet INT UNSIGNED NOT NULL,
    PRIMARY KEY (id_climate),
    FOREIGN KEY (id_planet) REFERENCES planet (id_planet)
);

CREATE TABLE terrain (
    id_terrain INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nm_terrain VARCHAR(50) NOT NULL,
    id_planet INT UNSIGNED NOT NULL,
    PRIMARY KEY (id_terrain),
    FOREIGN KEY (id_planet) REFERENCES planet (id_planet)
);
