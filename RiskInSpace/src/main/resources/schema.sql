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

Insert into planet(planet_name) values ('X');
