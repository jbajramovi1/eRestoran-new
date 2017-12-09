# --- !Ups
ALTER TABLE restaurant ALTER COLUMN mark TYPE DOUBLE PRECISION ;
# --- !Downs
ALTER TABLE restaurant ALTER COLUMN mark TYPE bigint;