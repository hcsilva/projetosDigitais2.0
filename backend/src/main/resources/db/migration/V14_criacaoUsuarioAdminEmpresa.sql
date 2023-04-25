insert into empresa (id, nome_estabelecimento, cnpj, telefone_contato, idioma, fuso) values (uuid_generate_v1(), 'cardápio digital Ltda', '18063114000146', '5432262626', 'pt-BR', 'z');
insert into usuarios values (uuid_generate_v1(), (select id from empresa), 'admin@cardapiodigital.com', '$2a$12$Nth2.JXetGmy/aFKTVVhhOI50R3TtQlCNoD6QxYevMtqACNvQBHVm', 'Usuário ADMIN', 'SIM');
insert into permissoes values(uuid_generate_v1(), 'ROLE_ADMIN');
insert into usuarios_permissoes (usuario_id, permissao_id) (select(select id from usuarios where login = 'admin@cardapiodigital.com' ) as usuario_id,
                                                                  (select id from permissoes where permissao_descricao = 'ROLE_ADMIN') as permissao_id);