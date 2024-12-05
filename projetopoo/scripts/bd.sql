create database if not exists projetopoo;

use projetopoo;

create table if not exists marca (
	id integer primary key auto_increment,
    nome varchar(100) not null,
    logo mediumblob
);

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    id_marca INT NOT NULL,
    logo MEDIUMBLOB,
    preco DOUBLE NOT NULL,
    descricao VARCHAR(1000),
    FOREIGN KEY (id_marca) REFERENCES marca(id)
);