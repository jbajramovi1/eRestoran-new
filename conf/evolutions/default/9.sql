# --- !Ups
UPDATE account SET role='ADMIN' WHERE email='admin@abhintern.ba';
# --- !Downs
DELETE FROM account WHERE role='ADMIN';
