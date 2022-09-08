create database demo;

CREATE table users(
	id int not null  auto_increment  primary key ,
    first_name varchar(25) not null,
    last_name varchar(25) not  null,
    age int not null
);

alter table users
drop birth_date ;

insert into users (first_name, last_name, age) 
values ('Erling', 'Halland',  22), ('Kylian', 'Mbappe', 21);

update users set first_name = 'Erling1' where last_name like 'H%';
select * from users;

delete from users where first_name = 'Erling1';

drop schema demo;

TRUNCATE TABLE users;