CREATE TABLE topicos (
   id bigint not null auto_increment primary key,
   titulo varchar(100) not null,
   mensaje varchar(250) not null,
   fechaDeCreacion date not null,
   estadoDelTopico tinyint(1) not null default 1,
   autor varchar(100) not null,
   curso varchar(100) not null,
   respuesta varchar (250) not null
);

