create database if not exists projetopoo;

use projetopoo;

create table if not exists marca (
    id integer primary key auto_increment,
    nome varchar(100) not null,
    logo mediumblob
    );
    
    create table if not exists produto(
    id integer primary key auto_increment,
    foto mediumblob,
    nome varchar(100) not null,
    marca varchar(100) not null,
    preco double not null
    );
    
    select * from marca;