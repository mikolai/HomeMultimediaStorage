CREATE TABLE security.users
(
  id serial NOT NULL,
  name character varying(50),
  password character(64),
  version integer,
  role_id integer,
  username character varying(50),
  CONSTRAINT users_pk PRIMARY KEY (id ),
  CONSTRAINT users_roles_fk FOREIGN KEY (role_id)
      REFERENCES security.roles (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);