CREATE TABLE photo_gallery.photos
(
  id serial NOT NULL,
  date date,
  catalogue_id integer,
  user_id integer,
  version integer,
  topic character varying(100),
  description character varying(500),
  tags character varying(300),
  photo_data bytea,
  CONSTRAINT photos_pk PRIMARY KEY (id ),
  CONSTRAINT photos_catalogues_fk FOREIGN KEY (catalogue_id)
      REFERENCES photo_gallery.catalogues (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT photos_users_fk FOREIGN KEY (user_id)
      REFERENCES security.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);