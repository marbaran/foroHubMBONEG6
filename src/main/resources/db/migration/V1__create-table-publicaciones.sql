create table publicaciones(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(300) not null,
    fecha datetime not null,
    status varchar(10) not null,
    autor_id bigint not null,
    curso varchar(20) not null,

    primary key (id)
);
