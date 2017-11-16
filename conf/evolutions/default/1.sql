# --- !Ups
CREATE TABLE request_log(
	id BIGINT NOT NULL,
	name text,
	type text,
	CONSTRAINT request_log_pk PRIMARY KEY (id)
) WITH (
	OIDS = FALSE
);

# --- !Downs
DROP TABLE IF EXISTS request_log;