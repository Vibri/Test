drop database if exists bookdb;

drop user if exists 'bookadmin'@'%';

drop user if exists 'bookuser'@'%';

create database if not exists bookdb character set utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF nOT EXISTS 'bookadmin'@'%' IDENTIFIED WITH mysql_native_password by 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `bookdb`.* TO `bookadmin`@`%`;
CREATE USER IF NOT EXISTS `bookuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `bookdb`.* TO `bookuser`@`%`;
FLUSH PRIVILEGES;

