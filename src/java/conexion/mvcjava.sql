create table usuario(
    cedula bigint not null primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    rol enum('admin','user','supervisor'),
    correo varchar(50)
);

describe usuario;

select * from usuario;
