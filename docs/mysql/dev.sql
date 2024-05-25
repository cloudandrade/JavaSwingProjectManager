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

INSERT INTO ods (id, title) values (1,"ERRADICAÇÃO DA POBREZA");
INSERT INTO ods (id,title) values (2,"FOME ZERO E AGRICULTURA SUSTENTÁVEL");
INSERT INTO ods (id,title) values (3,"SAÚDE E BEM-ESTAR");
INSERT INTO ods (id,title) values (4,"EDUCAÇÃO DE QUALIDADE");
INSERT INTO ods (id,title) values (5,"IGUALDADE DE GÊNERO");
INSERT INTO ods (id,title) values (6,"ÁGUA POTÁVEL");
INSERT INTO ods (id,title) values (7,"ENERGIA LIMPA E ACESSÍVEL");
INSERT INTO ods (id,title) values (8,"TRABALHO DECENTE E CRESCIMENTO ECONÔMICO");
INSERT INTO ods (id,title) values (9,"INDÚSTRIA, INOVAÇÃO E INFRAESTRUTURA");
INSERT INTO ods (id,title) values (10,"REDUÇÃO DAS DESIGUALDADES");
INSERT INTO ods (id,title) values (11,"CIDADES E COMUNIDADES SUSTENTÁVEIS");
INSERT INTO ods (id,title) values (12,"CONSUMO E PRODUÇÃO RESPONSÁVEIS");
INSERT INTO ods (id,title) values (13,"AÇÃO CONTRA A MUDANÇA GLOBAL DO CLIMA");
INSERT INTO ods (id,title) values (14,"VIDA NA ÁGUA");
INSERT INTO ods (id,title) values (15,"VIDA TERRESTRE");
INSERT INTO ods (id,title) values (16,"PAZ, JUSTIÇA E INSTITUIÇÕES EFICAZES");
INSERT INTO ods (id,title) values (17,"PARCERIAS E MEIOS DE IMPLEMENTAÇÃO");