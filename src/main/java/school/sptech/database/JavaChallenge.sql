DROP DATABASE javachallenge;
CREATE DATABASE JavaChallenge;

USE JavaChallenge;

CREATE TABLE `Server`(
	`idServer`INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `macAddress` char(17)
);

CREATE TABLE `ComponentType`(
	`idComponentType` INT PRIMARY KEY AUTO_INCREMENT,
    `type` VARCHAR(10)
);

CREATE TABLE `Component`(
	`idComponent` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `enable` BOOLEAN,
    `fkComponentType` INT,
    FOREIGN KEY (`fkComponentType`) REFERENCES `ComponentType`(`idComponentType`),
	`fkServer` INT,
    FOREIGN KEY (`fkServer`) REFERENCES `Server`(`idServer`) 
);

CREATE TABLE `Metric`(
	`idMetric` INT PRIMARY KEY AUTO_INCREMENT,
    `value` DECIMAL(6,2),
    `fkComponent` INT,
    FOREIGN KEY (`fkComponent`) REFERENCES `Component`(`idComponent`)
);

CREATE TABLE `Capture`(
	`idCapture` INT PRIMARY KEY AUTO_INCREMENT,
    `value` DECIMAL(6,2),
    `fkComponent` INT,
    FOREIGN KEY (`fkComponent`) REFERENCES `Component`(`idComponent`)
);

insert into ComponentType values 
(null,"CPU"),
(null,"RAM"),
(null,"DISK");
