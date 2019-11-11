DROP DATABASE IF EXISTS tavernsimulator;
CREATE DATABASE tavernsimulator;

USE tavernsimulator;

DROP TABLE IF EXISTS tavern;
CREATE TABLE tavern (
	tavernId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tavernName VARCHAR(50) NOT NULL,
    tavernOwner VARCHAR(50) NOT NULL,
    tavernAbout TEXT,
    tavernFunds INT NOT NULL,
    tavernDrinks INT NOT NULL,
    tavernFood INT NOT NULL,
    tavernRepairs INT NOT NULL
);
    
DROP TABLE IF EXISTS tavernReport;
CREATE TABLE tavernReport (
	tavernReportId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	tavernReportDay DATE NOT NULL,
    tavernReportRoll INT NOT NULL,
    tavernReportEarnings INT NOT NULL,
    tavernReportFunds INT NOT NULL,
    tavernId INT NOT NULL,
    CONSTRAINT fk_tavern_tavernReport FOREIGN KEY (tavernId) REFERENCES tavern (tavernId)
);