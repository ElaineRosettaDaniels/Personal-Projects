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
    dexBonus INT NOT NULL,
    strBonus INT NOT NULL,
    hitPoints INT NOT NULL,
    damThres INT NOT NULL,
    misThres INT NOT NULL,
    totalDist INT NOT NULL,
    ichorBoosted BOOL NOT NULL,
    ichorUses INT NOT NULL,
    maxRiders INT NOT NULL
);

DROP TABLE IF EXISTS station;
CREATE TABLE station (
	stationId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    stName VARCHAR(30) NOT NULL,
    armorBonus INT NOT NULL,
    stAction VARCHAR(40) NOT NULL,
    crewed BOOL NOT NULL
);

DROP TABLE IF EXISTS vehicleStations;
CREATE TABLE vehicleStations (
    vehicleId INT NOT NULL,
    stationId INT NOT NULL,
    PRIMARY KEY (vehicleId, stationId),
    FOREIGN KEY (vehicleId) REFERENCES vehicle (vehicleId),
    FOREIGN KEY (stationId) REFERENCES station (stationId)
);

DROP TABLE IF EXISTS rider;
CREATE TABLE rider (
	riderId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    riName VARCHAR(30) NOT NULL,
    armor INT NOT NULL,
    hitPoints INT NOT NULL
);

DROP TABLE IF EXISTS vehicleRiders;
CREATE TABLE vehicleRiders (
	vehicleId INT NOT NULL,
    riderId INT NOT NULL,
    PRIMARY KEY (vehicleId, riderId),
    FOREIGN KEY (vehicleId) REFERENCES vehicle (vehicleId),
    FOREIGN KEY (riderId) REFERENCES rider (riderId)
);

DROP TABLE IF EXISTS stationRider;
CREATE TABLE stationRider (
	stationId INT NOT NULL,
    riderId INT NOT NULL,
    PRIMARY KEY (stationId, riderId),
    FOREIGN KEY (stationId) REFERENCES station (stationId),
    FOREIGN KEY (riderId) REFERENCES rider (riderId)
);