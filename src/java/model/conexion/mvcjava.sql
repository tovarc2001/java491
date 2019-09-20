/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Nanimo
 * Created: jul 28, 2019
 */
create database mvcjava;
use mvcjava;
create table usuario(
    cedula bigint not null primary key auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    correo varchar(50) not null,
    clave varchar(50) not null,
    rol enum('admin', 'user') not null
);

describe usuario;


insert into usuario (cedula,nombre, apellido, rol, correo, clave) values(1111111,"Carlos","Vargas","admin","cv@cv.co",md5("12345678")),
(2222222,"Jaime","Puentes","user","jp@jp.co",md5("12345678")),(3333333,"Zaida","Rivera","admin","zr@zr.co",md5("12345678"));


select * from usuario where 1;

create table evidencia(
	id int primary key not null auto_increment,
	cedula bigint not null,
	nombre varchar(50) not null,
	ruta varchar(50) not null,
	foreign key (cedula) references usuario(cedula)
)