# --- !Ups
ALTER TABLE restaurant ALTER COLUMN description TYPE TEXT;
# --- !Downs
ALTER TABLE restaurant ALTER COLUMN description TYPE VARCHAR(255);