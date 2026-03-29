drop database if exists orderservice;

drop user if exists 'orderserviceadmin'@'%';

drop user if exists 'orderserviceuser'@'%';

create database if not exists orderservice character set utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'orderserviceadmin'@'%' IDENTIFIED WITH mysql_native_password by 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `orderservice`.* TO `orderserviceadmin`@`%`;
CREATE USER IF NOT EXISTS `orderserviceuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `orderservice`.* TO `orderserviceuser`@`%`;
FLUSH PRIVILEGES;