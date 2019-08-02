/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Nanimo
 * Created: jul 28, 2019
 */

create table usuario(
    cedula bigint not null primary key auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    rol enum('admin', 'user') not null
);

describe usuario;


insert into usuario values(1234,"Carlos","Vargas","admin"),
(4444,"Jaime","Puentes","user"),(5555,"Zaida","Rivera","admin");


select * from usuario where 1;
