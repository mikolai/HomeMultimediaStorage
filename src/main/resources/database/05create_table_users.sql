CREATE TABLE security.users
(
  id serial NOT NULL,
  name character varying(50),
  password character(64),
  version integer,
  username character varying(50),
  CONSTRAINT users_pk PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);