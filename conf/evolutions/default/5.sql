 # --- !Ups
create table comment(
id bigint not null,
content text,
mark float not null,
insert_date date,
account_id bigint,
restaurant_id bigint,
constraint comment_pk_test primary key (id),
constraint comment_restaurant_id_fk FOREIGN KEY (restaurant_id) references restaurant(id) match simple on update no action on delete cascade,
constraint comment_user_id_fk FOREIGN KEY (account_id) references account(id) match simple on update no action on delete cascade);

 # --- !Downs
 drop table if exists comment;