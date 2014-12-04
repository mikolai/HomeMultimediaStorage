CREATE TABLE photo_gallery.catalogues
(
  id serial NOT NULL,
  parent_id integer,
  name character varying(100)[],
  user_id integer,
  version integer,
  CONSTRAINT catalogues_pk PRIMARY KEY (id ),
  CONSTRAINT catalogues_rfk FOREIGN KEY (parent_id)
      REFERENCES photo_gallery.catalogues (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT catolugues_users_fk FOREIGN KEY (user_id)
      REFERENCES security.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);