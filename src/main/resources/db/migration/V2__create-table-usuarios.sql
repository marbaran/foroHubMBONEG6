create table usuarios(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null,
    contrasena varchar(50) not null,

    primary key (id)
);
