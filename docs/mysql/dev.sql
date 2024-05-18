CREATE SCHEMA `odsmanager`;

USE `odsmanager`;

CREATE TABLE IF NOT EXISTS `User` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`user` varchar(50),
	`name` varchar(255),
	`email` varchar(255),
	`phone` varchar(255) NOT NULL,
	`password` varchar(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ODS` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`title` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Project` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`title` varchar(255) NOT NULL,
	`description` varchar(255) NOT NULL,
	`owner_id` int NOT NULL,
	`ods_id` int NOT NULL,
	`created_at` timestamp NOT NULL,
	`status` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Project` ADD CONSTRAINT `Project_fk3` FOREIGN KEY (`owner_id`) REFERENCES `User`(`id`);

ALTER TABLE `Project` ADD CONSTRAINT `Project_fk4` FOREIGN KEY (`ods_id`) REFERENCES `ODS`(`id`);
