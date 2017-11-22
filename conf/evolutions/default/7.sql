# --- !Ups
INSERT INTO account (id,email,password,role,phone,country,city,first_name,last_name) VALUES (5,'admin@abh.ba','$2y$10$nDBBMbUbZJa/s1XTzjlV6.3YOwgm/uAwwI12z2KkxH16Q0KKwdpDy','ADMIN','000','Bosnia and Herzegovina','Sarajevo','admin','admin');
# --- !Downs
DELETE FROM account WHERE role='admin';