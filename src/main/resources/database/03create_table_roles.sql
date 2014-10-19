CREATE TABLE security.roles
(
  id serial NOT NULL,
  name character varying(100)[],
  version integer,
  CONSTRAINT roles_pk PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);