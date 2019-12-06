DROP DATABASE IF EXISTS supertracktest;
CREATE DATABASE supertracktest;

USE supertracktest;

DROP TABLE IF EXISTS superhuman;
CREATE TABLE superhuman (
	SuperId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    SuperName VARCHAR(35) NOT NULL,
    SuperDossier VARCHAR(255) NOT NULL,
    IsVillain BOOL NOT NULL,
    PowerClass VARCHAR(30)
);

DROP TABLE IF EXISTS locale;
CREATE TABLE locale (
	LocaleId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    LocaleName VARCHAR(30) NOT NULL,
    LocaleDossier VARCHAR(255) NOT NULL,
    Address VARCHAR(30),
    District VARCHAR(30),
    City VARCHAR(30) NOT NULL,
    Country VARCHAR(30) NOT NULL,
    LongLat VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS org;
CREATE TABLE org (
	OrgId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    OrgName VARCHAR(30) NOT NULL,
    OrgDossier VARCHAR(255) NOT NULL,
    LocaleId INT,
    CONSTRAINT fk_locale_org FOREIGN KEY (LocaleId) REFERENCES locale (LocaleId)
);

DROP TABLE IF EXISTS superorg;
CREATE TABLE superorg (
	RelId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    SuperId INT NOT NULL,
    OrgId INT NOT NULL,
    CONSTRAINT fk_superhuman_superorg FOREIGN KEY (SuperId) REFERENCES superhuman (SuperId),
    CONSTRAINT fk_org_superorg FOREIGN KEY (OrgId) REFERENCES org (OrgId)
);

DROP TABLE IF EXISTS sighting;
CREATE TABLE sighting (
	SightId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    SightDate DATE NOT NULL,
    SuperId INT NOT NULL,
    LocaleId INT NOT NULL,
    CONSTRAINT fk_superhuman_sighting FOREIGN KEY (SuperId) REFERENCES superhuman (SuperId),
    CONSTRAINT fk_locale_sighting FOREIGN KEY (LocaleID) REFERENCES Locale (LocaleID)
);