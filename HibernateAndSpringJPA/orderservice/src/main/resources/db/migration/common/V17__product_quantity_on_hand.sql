alter table product
    add column quantity_on_hand integer;

UPDATE product
SET quantity_on_hand = 10;