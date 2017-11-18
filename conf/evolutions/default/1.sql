 # --- !Ups
create table account(
id bigint not null,
email varchar(255) not null,
password varchar(255) not null,
role varchar(255) ,
phone varchar(255),
country varchar(255),
city varchar(255),
first_name varchar(255),
last_name varchar(255),
constraint account_pk_test primary key (id));

 # --- !Downs
 drop table if exists account;