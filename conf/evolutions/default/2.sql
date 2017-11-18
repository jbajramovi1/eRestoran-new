# --- !Ups
ALTER TABLE account ADD CONSTRAINT account_email_unique UNIQUE (email);
# --- !Downs
ALTER TABLE account DROP CONSTRAINT IF EXISTS account_email_unique;