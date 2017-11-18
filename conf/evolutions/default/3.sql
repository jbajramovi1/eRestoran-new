# --- !Ups
create table restaurant(
id bigint not null,
name varchar(255),
description text not null,
mark bigint ,
votes bigint,
price_range bigint,
image_file_name varchar(255),
cover_file_name varchar(255),
category varchar(255),
constraint restaurant_pk_test primary key (id));

 # --- !Downs
 drop table if exists restaurant;
