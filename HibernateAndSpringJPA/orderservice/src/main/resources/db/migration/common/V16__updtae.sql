UPDATE order_header
SET version = 0 where version is null;

UPDATE order_line
SET version = 0 where version is null;