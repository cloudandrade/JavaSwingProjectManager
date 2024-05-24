CREATE SCHEMA `odsmanager`;

USE `odsmanager`;

CREATE TABLE IF NOT EXISTS `User` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`username` varchar(50),
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

INSERT INTO user (username, name, email, phone, password) VALUES ("adm", "admin", "admin@pm.com", "000000000", "$2a$10$5rREqwYq0bX0o4jG/3uP3ejx1Y9bB.ftmsmsoOHhLrRiDtFkoND8q"); 
-- encrypted password Admin@24k

INSERT INTO ods (title) values ("ERRADICAÇÃO DA POBREZA");
INSERT INTO ods (title) values ("FOME ZERO E AGRICULTURA SUSTENTÁVEL");
INSERT INTO ods (title) values ("SAÚDE E BEM-ESTAR");
INSERT INTO ods (title) values ("EDUCAÇÃO DE QUALIDADE");
INSERT INTO ods (title) values ("IGUALDADE DE GÊNERO");
INSERT INTO ods (title) values ("ÁGUA POTÁVEL");
INSERT INTO ods (title) values ("ENERGIA LIMPA E ACESSÍVEL");
INSERT INTO ods (title) values ("TRABALHO DECENTE E CRESCIMENTO ECONÔMICO");
INSERT INTO ods (title) values ("INDÚSTRIA, INOVAÇÃO E INFRAESTRUTURA");
INSERT INTO ods (title) values ("REDUÇÃO DAS DESIGUALDADES");
INSERT INTO ods (title) values ("CIDADES E COMUNIDADES SUSTENTÁVEIS");
INSERT INTO ods (title) values ("CONSUMO E PRODUÇÃO RESPONSÁVEIS");
INSERT INTO ods (title) values ("AÇÃO CONTRA A MUDANÇA GLOBAL DO CLIMA");
INSERT INTO ods (title) values ("VIDA NA ÁGUA");
INSERT INTO ods (title) values ("VIDA TERRESTRE");
INSERT INTO ods (title) values ("PAZ, JUSTIÇA E INSTITUIÇÕES EFICAZES");
INSERT INTO ods (title) values ("PARCERIAS E MEIOS DE IMPLEMENTAÇÃO");