drop database if exists bookdb2;

drop user if exists 'bookdb2admin'@'%';

drop user if exists 'bookdb2user'@'%';

create database if not exists bookdb2 character set utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'bookdb2admin'@'%' IDENTIFIED WITH mysql_native_password by 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `bookdb2`.* TO `bookdb2admin`@`%`;
CREATE USER IF NOT EXISTS `bookdb2user`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `bookdb2`.* TO `bookdb2user`@`%`;
FLUSH PRIVILEGES;