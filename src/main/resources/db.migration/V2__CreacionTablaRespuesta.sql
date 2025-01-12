CREATE TABLE respuestas (
   id bigint not null auto_increment primary key,
   mensaje varchar(250) not null,
   fechaDeCreacion date not null,
   topico varchar(100) not null,
   autor varchar(100) not null,
   solucion (250) not null
);

primary key(id)
