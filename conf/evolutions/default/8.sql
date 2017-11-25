# --- !Ups
INSERT INTO account (id,email,password,role,phone,country,city,first_name,last_name) VALUES (10,'admin@abh.ba','$2a$10$RwgqamsekzYxRrU8uC9./.e4Ej25x54OqawnyZiv4ioKQ2iMS/Dxi','ADMIN','000','Bosnia and Herzegovina','Sarajevo','admin','admin');
# --- !Downs
DELETE FROM account WHERE role='ADMIN';
