drop database if exists wp;

drop user if exists 'wpadmin'@'%';

drop user if exists 'wpuser'@'%';

create database if not exists wp character set utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'wpadmin'@'%' IDENTIFIED WITH mysql_native_password by 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `wp`.* TO `wpadmin`@`%`;
CREATE USER IF NOT EXISTS `wpuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `wp`.* TO `wpuser`@`%`;
FLUSH PRIVILEGES;