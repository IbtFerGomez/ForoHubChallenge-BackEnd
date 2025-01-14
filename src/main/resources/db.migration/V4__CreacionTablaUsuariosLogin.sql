CREATE TABLE usuarioLogin (
       id BIGINT NOT NULL auto_increment,
       login VARCHAR(255) NOT NULL,
       password VARCHAR(300) NOT NULL,
       CONSTRAINT unique_id_login_password UNIQUE (id, login, password)

       PRIMARY KEY  (id)
   );