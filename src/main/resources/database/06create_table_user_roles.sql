CREATE TABLE security.user_roles
(
   user_id integer, 
   role_id integer, 
   CONSTRAINT user_roles_pk PRIMARY KEY (user_id, role_id), 
   CONSTRAINT user_roles_user_fk FOREIGN KEY (user_id) REFERENCES security.users (id) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT user_roles_roles_fk FOREIGN KEY (role_id) REFERENCES security.roles (id) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
);