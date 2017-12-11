# --- !Ups
ALTER TABLE reservation ADD COLUMN restaurant_table_id bigint;
ALTER TABLE ADD CONSTRAINT reservation_restaurant_table_id_fk FOREIGN KEY (restaurant_table_id) references restaurant_table(id) match simple on update no action on delete cascade;

# --- !Downs
ALTER TABLE reservation DROP COLUMN  IF EXISTS restaurant_table_id;
ALTER TABLE reservation DROP CONSTRAINT IF EXISTS reservation_restaurant_table_id_fk;