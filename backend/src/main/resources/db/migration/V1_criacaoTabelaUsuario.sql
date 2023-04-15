create table usuarios(
  id serial not null,
  login varchar(255) not null,
  senha varchar(255) not null,
  nome varchar(255) not null,
  telefone varchar(20) not null,
  ativo varchar(3) not null default 'SIM',

  primary key (id),
  unique (login, telefone)
);