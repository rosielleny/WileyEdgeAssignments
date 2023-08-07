CREATE SCHEMA SuperheroSightings;
USE SuperheroSightings;

DROP TABLE IF EXISTS SupeOrganisation;
DROP TABLE IF EXISTS Sighting;
DROP TABLE IF EXISTS Supe;
DROP TABLE IF EXISTS Organisation;
DROP TABLE IF EXISTS Location;


CREATE TABLE Supe(
  supeId INT PRIMARY KEY AUTO_INCREMENT,
  supeName VARCHAR(100) NOT NULL,
  supePower VARCHAR(100),
  supeDescription VARCHAR(250));

CREATE TABLE Location(
locationId INT PRIMARY KEY AUTO_INCREMENT,
locationName VARCHAR(30),
locationDescription VARCHAR(100),
locationAddress VARCHAR(30),
locationCoordinates VARCHAR(30));

CREATE TABLE Sighting(
sightingId INT PRIMARY KEY AUTO_INCREMENT,
dateSeen DATE,
supeId INT,
locationId INT,
CONSTRAINT FOREIGN KEY (supeId) REFERENCES Supe(supeId),
CONSTRAINT FOREIGN KEY (locationId) REFERENCES Location(locationId));

CREATE TABLE Organisation(
organisationId INT PRIMARY KEY AUTO_INCREMENT,
organisationName VARCHAR(30),
organisationDescription VARCHAR(100),
organisationAddress VARCHAR(30),
organisationContactInfo VARCHAR(50));

CREATE TABLE SupeOrganisation(
supeId INT,
organisationId INT,
CONSTRAINT FOREIGN KEY (supeId) REFERENCES Supe(supeId),
CONSTRAINT FOREIGN KEY (organisationId) REFERENCES Organisation(organisationId));

INSERT INTO Supe (supeName, supePower, supeDescription) VALUES
  ('Superman', 'Flight, Super strength', 'Hero'),
  ('Spider-Man', 'Wall-crawling, Spider-sense', 'Hero'),
  ('Wonder Woman', 'Superhuman strength, Lasso of Truth', 'Hero'),
  ('Iron Man', 'Powered armor, Genius intellect', 'Hero'),
  ('Captain America', 'Enhanced strength, Shield', 'Hero'),
  ('Black Widow', 'Espionage, Martial arts', 'Hero'),
  ('Thor', 'Control over lightning, Immortality', 'Hero'),
  ('Hulk', 'Superhuman strength, Regeneration', 'Hero'),
  ('Batman', 'Combat skills, Gadgets', 'Hero'),
  ('Flash', 'Super speed, Time travel', 'Hero'),
  ('Joker', 'Insanity, Mastermind', 'Villain'),
  ('Lex Luthor', 'Genius intellect, Wealth', 'Villain'),
  ('Thanos', 'Superhuman strength, Infinity Gauntlet', 'Villain'),
  ('Green Goblin', 'Goblin serum, Technology', 'Villain'),
  ('Loki', 'Shape-shifting, Illusions', 'Villain'),
  ('Magneto', 'Magnetism manipulation', 'Villain'),
  ('Venom', 'Symbiote bonding, Super strength', 'Villain'),
  ('Catwoman', 'Acrobatics, Stealth', 'Villain'),
  ('Red Skull', 'Superhuman strength, Nazi ideology', 'Villain'),
  ('Darkseid', 'Godlike powers, Immortality', 'Villain');

  INSERT INTO Location (locationName, locationDescription, locationAddress, locationCoordinates) VALUES
  ('New York City', 'Metropolis', '123 Main St', '40.7128, -74.0060'),
  ('Gotham City', 'Crime-ridden city', '456 Elm St', '41.8781, -87.6298'),
  ('Themyscira', 'Island paradise', '789 Oak St', '37.9838, 23.7275'),
  ('Asgard', 'Realm of gods', '987 Pine St', '34.0522, -118.2437'),
  ('Star City', 'City protected by Green Arrow', '654 Birch St', '32.7157, -117.1611'),
  ('Central City', 'Home of The Flash', '321 Cedar St', '29.7604, -95.3698'),
  ('Wakanda', 'Technologically advanced nation', '567 Maple St', '25.7617, -80.1918'),
  ('Atlantis', 'Underwater kingdom', '890 Walnut St', '33.7490, -84.3880'),
  ('Genosha', 'Mutant homeland', '345 Pine St', '42.3601, -71.0589'),
  ('Arkham Asylum', 'Mental institution for criminals', '543 Oak St', '39.9526, -75.1652');
  
  INSERT INTO Sighting (dateSeen, supeId, locationId) VALUES
  ('2023-06-15', 1, 1),
  ('2023-06-16', 2, 2),
  ('2023-06-17', 3, 3),
  ('2023-06-18', 4, 4),
  ('2023-06-19', 5, 5),
  ('2023-06-20', 6, 6),
  ('2023-06-21', 7, 7),
  ('2023-06-22', 8, 8),
  ('2023-06-23', 9, 9),
  ('2023-06-24', 10, 10);
  
  INSERT INTO Organisation (organisationName, organisationDescription, organisationAddress, organisationContactInfo) VALUES
  ('Justice League', 'Superhero team', '123 Oak St', 'contact@justiceleague.com'),
  ('Avengers', 'Superhero team', '456 Elm St', 'contact@avengers.com'),
  ('X-Men', 'Mutant team', '789 Pine St', 'contact@xmen.com'),
  ('Sinister Six', 'Villain team', '987 Cedar St', 'contact@sinistersix.com'),
  ('League of Shadows', 'Assassin organization', '654 Walnut St', 'contact@leagueofshadows.com'),
  ('Hydra', 'Terrorist organization', '321 Birch St', 'contact@hydra.com'),
  ('S.H.I.E.L.D.', 'International intelligence agency', '567 Oak St', 'contact@shield.com'),
  ('Brotherhood of Mutants', 'Mutant organization', '890 Elm St', 'contact@brotherhoodofmutants.com'),
  ('Legion of Doom', 'Villain team', '345 Pine St', 'contact@legionofdoom.com'),
  ('Squadron Supreme', 'Superhero team', '543 Cedar St', 'contact@squadronsupreme.com');
  
  INSERT INTO SupeOrganisation (supeId, organisationId) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 2),
  (2, 3),
  (2, 4),
  (3, 1),
  (3, 4),
  (4, 2),
  (5, 1),
  (5, 3),
  (6, 2),
  (6, 4),
  (7, 1),
  (7, 3),
  (7, 4),
  (8, 2),
  (8, 3),
  (8, 4),
  (9, 1),
  (9, 3),
  (10, 2),
  (10, 4);