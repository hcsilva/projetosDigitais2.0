create table usuarios(
  id serial not null,
  id_empresa uuid not null,
  login varchar(255) not null,
  senha varchar(255) not null,
  nome varchar(255) not null,
  ativo varchar(3) not null default 'SIM',

  primary key (id),
  unique (login)
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";