ALTER TABLE empresa ADD COLUMN data_criacao TIMESTAMP NOT NULL DEFAULT NOW();


insert into empresa (id, razao_social, cnpj, telefone_contato, email, idioma, fuso) values (uuid_generate_v1(), 'Xis Caxias', '00159263487', '5432262626', 'xiscaxias@gmail.com', 'pt-BR', 'z');
insert into usuarios values (nextval('usuarios_id_seq'), (select id from empresa), 'admin@cardapiodigital.com', '$2a$12$Nth2.JXetGmy/aFKTVVhhOI50R3TtQlCNoD6QxYevMtqACNvQBHVm', 'Usuário ADMIN', 'SIM');