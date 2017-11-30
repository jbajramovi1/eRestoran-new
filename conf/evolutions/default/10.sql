# --- !Ups
create table IF NOT EXISTS restaurant_table(
id bigint not null,
sitting_places int not null,
restaurant_id bigint,
constraint restaurant_table_pk_test primary key (id),
constraint table_restaurant_id_fk FOREIGN KEY (restaurant_id) references restaurant(id) match simple on update no action on delete cascade);

create table IF NOT EXISTS location(
id bigint not null,
name varchar(255) not null,
latitude float not null,
longitude float not null,
constraint location_pk_test primary key (id));

create table IF NOT EXISTS menu(
id bigint not null,
name varchar(255) not null,
restaurant_id bigint,
constraint menu_pk_test primary key (id),
constraint menu_restaurant_id_fk FOREIGN KEY (restaurant_id) references restaurant(id) match simple on update no action on delete cascade);

create table IF NOT EXISTS menu_item(
id bigint not null,
name varchar(255) not null,
price float,
description text,
menu_id bigint,
constraint menu_item_pk_test primary key (id),
constraint menuitem_menu_id_fk FOREIGN KEY (menu_id) references menu(id) match simple on update no action on delete cascade);


 # --- !Downs
drop table if exists restaurant_table;
drop table if exists location;
drop table if exists menu;
drop table if exists menu_item;