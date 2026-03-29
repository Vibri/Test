create table customer (
    id bigint not null auto_increment primary key,
    customer_name varchar(50),
    address varchar(30),
    city varchar(30),
    state varchar(30),
    zip_code varchar(30),
    phone varchar(20),
    email varchar(255),
    created_date timestamp,
    last_modified_date timestamp
)  engine = InnoDB;

alter table order_header
    add column customer_id bigint;
alter table order_header add constraint customer_id_fk
foreign key (customer_id) references customer(id);

ALTER TABLE order_header
DROP COLUMN customer_name;

insert into customer (customer_name, address, city, state, zip_code, phone, email, created_date, last_modified_date)
values ('Customer name', 'Street', 'City', 'State', '123', '111222333', 'mail@mail.com', now(), now());

UPDATE order_header
SET customer_id = (SELECT id FROM customer WHERE customer_name = 'Customer name');
