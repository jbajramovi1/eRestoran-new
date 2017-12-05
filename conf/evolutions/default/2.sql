# --- !Ups
ALTER TABLE account ADD CONSTRAINT acc_email_unique UNIQUE (email);
# --- !Downs
ALTER TABLE account DROP CONSTRAINT IF EXISTS acc_email_unique;