create table endereco (
  id uuid not null,
  logradouro varchar not null,
  numero int not null,
  bairro varchar not null,
  complemento varchar,
  cep int not null,
  cidade varchar not null,
  estado varchar not null,
  pais varchar not null,
  primary key (id)
);

create table horario_funcionamento (
  id uuid not null,
  segunda_ini time without time zone,
  segunda_fim time without time zone,
  segunda_ini_2 time without time zone,
  segunda_fim_2 time without time zone,
  terca_ini time without time zone,
  terca_fim time without time zone,
  terca_ini_2 time without time zone,
  terca_fim_2 time without time zone,
  quarta_ini time without time zone,
  quarta_fim time without time zone,
  quarta_ini_2 time without time zone,
  quarta_fim_2 time without time zone,
  quinta_ini time without time zone,
  quinta_fim time without time zone,
  quinta_ini_2 time without time zone,
  quinta_fim_2 time without time zone,
  sexta_ini time without time zone,
  sexta_fim time without time zone,
  sexta_ini_2 time without time zone,
  sexta_fim_2 time without time zone,
  sabado_ini time without time zone,
  sabado_fim time without time zone,
  sabado_ini_2 time without time zone,
  sabado_fim_2 time without time zone,
  domingo_ini time without time zone,
  domingo_fim time without time zone,
  domingo_ini_2 time without time zone,
  domingo_fim_2 time without time zone,
  data_alteracao TIMESTAMP NOT NULL DEFAULT NOW(),
  data_criacao TIMESTAMP NOT NULL DEFAULT NOW(),
  primary key (id)
);

create table empresa (
  id uuid not null,
  id_endereco uuid,
  id_horario uuid,
  nome_estabelecimento varchar(255) not null,
  cnpj varchar(20),
  logo oid,
  imagem_capa oid,
  descricao varchar(300),
  site varchar(100),
  instagram varchar(100),
  facebook varchar(100),
  whatsapp varchar(20),
  telefone_contato varchar(20) not null,
  idioma varchar(5) not null,
  fuso varchar(10) not null,
  primary key (id),
  constraint fk_empresa_endereco foreign key (id_endereco) references endereco,
  constraint fk_empresa_horario foreign key (id_horario) references horario_funcionamento
);

create table forma_pagamento (
  id uuid not null,
  id_empresa uuid not null,
  dinheiro boolean default false,
  cartao_credito boolean default false,
  cartao_debito boolean default false,
  vale_refeicao boolean default false,
  visivel boolean default false,
  primary key (id),
  constraint fk_forma_pagamento_empresa foreign key (id_empresa) references empresa
);

create table links (
  id uuid not null,
  id_empresa uuid not null,
  descricao varchar(300) not null,
  link varchar(100) not null,
  primary key (id),
  constraint fk_links_empresa foreign key (id_empresa) references empresa
);

create table cardapio (
  id uuid not null,
  id_empresa uuid not null,
  descricao varchar(100) not null,
  descricao_detalhada varchar(250) not null,
  label_mini_prato varchar(30),
  status boolean default false,
  data_alteracao TIMESTAMP NOT NULL DEFAULT NOW(),
  data_criacao TIMESTAMP NOT NULL DEFAULT NOW(),
  primary key (id),
  constraint fk_cardapio_empresa foreign key (id_empresa) references empresa
);

create table categoria (
  id uuid not null,
  id_empresa uuid not null,
  id_cardapio uuid not null,
  descricao varchar(100) not null,
  descricao_detalhada varchar(250) not null,
  label_mini_prato varchar(30),
  status boolean default false,
  primary key (id),
  constraint fk_categoria_empresa foreign key (id_empresa) references empresa,
  constraint fk_categoria_cardapio foreign key (id_cardapio) references empresa
);

create table item_categoria (
  id uuid not null,
  id_empresa uuid not null,
  id_cardapio uuid not null,
  id_categoria uuid,
  descricao_detalhada varchar(250) not null,
  descricao_simples varchar(100) not null,
  foto oid,
  preco numeric(5, 2) not null,
  preco_mini_prato numeric(5, 2),
  serve_quantidade_pessoas int default 1,
  sem_lactose boolean default false,
  vegetariano boolean default false,
  sem_gluten boolean default false,
  vegano boolean default false,
  destaque boolean default false,
  primary key (id),
  constraint fk_item_categoria_empresa foreign key (id_empresa) references empresa,
  constraint fk_item_categoria_cardapio foreign key (id_cardapio) references cardapio,
  constraint fk_item_categoria_categoria foreign key (id_categoria) references categoria
);

create table item_extras (
  id uuid not null,
  id_empresa uuid not null,
  id_item_categoria uuid not null,
  descricao_detalhada varchar(250) not null,
  primary key (id),
  constraint fk_item_categoria_empresa foreign key (id_empresa) references empresa,
  constraint fk_item_extras_item_categoria foreign key (id_item_categoria) references item_categoria
);