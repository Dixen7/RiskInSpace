CREATE DATABASE riskinspace;

\c riskinspace;

CREATE TABLE species (
 species_id serial NOT NULL,
 species_name VARCHAR(100) NOT NULL,
 primary key (species_id)
);

Create table player (
 player_id serial not null,
 player_name varchar(100),
 player_money int default 0,
 player_species_id int not null,
 primary key (player_id),
 CONSTRAINT FK_SPEC FOREIGN KEY (player_species_id) REFERENCES species (species_id)
);

Create table card (
 card_id serial not null,
 card_name varchar(100) not null,
 card_description text,
 card_image varchar(255),
 primary key (card_id)
);

Create table planet (
 planet_id serial not null,
 planet_name varchar(100),
 planet_ships_nbr int default 1,
 planet_owner int,
 planet_bonus int,
 planet_image varchar(255),
 primary key (planet_id),
 CONSTRAINT FK_PLAY FOREIGN KEY (planet_owner) REFERENCES player (player_id)
);

Create table frontiers (
 planet_id1 int not null,
 planet_id2 int not null,
 primary key (planet_id1 , planet_id2),
 CONSTRAINT FK_PLA1 FOREIGN KEY (planet_id1) REFERENCES planet (planet_id), 
 CONSTRAINT FK_PLA2 FOREIGN KEY (planet_id2) REFERENCES planet (planet_id)
);

Insert into planet(planet_name, planet_bonus)
 values
 ('1', 42),
 ('2', 42),
 ('3', 42),
 ('4', 42),
 ('5', 42),
 ('6', 42),
 ('7', 42),
 ('8', 42),
 ('9', 42),
 ('10', 42),
 ('11', 42),
 ('12', 42),
 ('13', 42),
 ('14', 42),
 ('15', 42),
 ('16', 42),
 ('17', 42),
 ('18', 42),
 ('19', 42),
 ('20', 42),
 ('21', 42),
 ('22', 42),
 ('23', 42),
 ('24', 42),
 ('25', 42),
 ('26', 42),
 ('27', 42),
 ('28', 42),
 ('29', 42),
 ('30', 42),

Insert into frontiers(planet_id1, planet_id2)
 values
 (1, 2),
 (1, 4),
 (1, 5),
 (1, 25),
 (2, 3),
 (2, 4),
 (3, 4),
 (3, 11),
 (4, 5),
 (5, 6),
 (6, 7),
 (6, 9),
 (7, 8),
 (7, 9),
 (8, 9),
 (8, 10),
 (9, 10)
 (9, 16),
 (10, 13),
 (11, 12),
 (12, 13),
 (12, 14),
 (12, 15),
 (13, 14),
 (13, 21),
 (14, 15),
 (14, 17),
 (15, 17),
 (16, 17),
 (16, 18),
 (17, 18),
 (17, 19),
 (17, 20),
 (18, 20),
 (19, 20),
 (19, 23),
 (20, 28),
 (21, 22),
 (21, 23),
 (22, 24),
 (22, 25),
 (23, 24),
 (23, 26),
 (24, 25),
 (24, 26),
 (25, 26),
 (26, 27),
 (27, 28),
 (27, 29),
 (28, 29),
 (28, 30),
 (29, 30);