DROP DATABASE IF EXISTS infernal_escape;
CREATE DATABASE infernal_escape;

USE infernal_escape;

DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
	vehicleId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    veName VARCHAR(30) NOT NULL,
    veType VARCHAR(30) NOT NULL,
    armor INT NOT NULL,
    speed INT NOT NULL,
    hitPoints INT NOT NULL,
    damThres INT NOT NULL,
    misThres INT NOT NULL,
    position INT NOT NULL,
    ichorBoosted BOOL NOT NULL,
    ichorUses INT NOT NULL,
    maxCrew INT NOT NULL
);

DROP TABLE IF EXISTS station;
CREATE TABLE station (
	stationId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    stName VARCHAR(30) NOT NULL,
    armorBonus INT NOT NULL,
    stAction TEXT NOT NULL,
    crewed BOOL NOT NULL
);

DROP TABLE IF EXISTS vehicleStations;
CREATE TABLE vehicleStations (
	relId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    vehicleId INT NOT NULL,
    stationId INT NOT NULL,
    CONSTRAINT fk_vehicle_vehicleStations FOREIGN KEY (vehicleId) REFERENCES vehicle (vehicleId),
    CONSTRAINT fk_station_vehicleStations FOREIGN KEY (stationId) REFERENCES station (stationId)
);

DROP TABLE IF EXISTS crew;
CREATE TABLE crew (
	crewId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    crName VARCHAR(30) NOT NULL,
    armor INT NOT NULL,
    hitPoints INT NOT NULL,
    attack INT NOT NULL,
    stationId INT,
    CONSTRAINT fk_station_crew FOREIGN KEY (stationId) REFERENCES station (stationId)
);

DROP TABLE IF EXISTS vehicleCrew;
CREATE TABLE vehicleCrew (
	vehicleId INT NOT NULL,
    crewId INT NOT NULL,
    CONSTRAINT fk_vehicle_vehicleCrew FOREIGN KEY (vehicleId) REFERENCES vehicle (vehicleId),
    CONSTRAINT fk_crew_vehicleCrew FOREIGN KEY (crewId) REFERENCES crew (crewId)
);