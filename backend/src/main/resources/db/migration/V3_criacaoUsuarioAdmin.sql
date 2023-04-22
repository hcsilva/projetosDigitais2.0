
--insert into empresa values (uuid_generate_v1(), 'Xis Caxias', '00159263487', null, null, null, null, null, null, null, null, '5432262626', 'xiscaxias@gmail.com', 'pt-BR', 'z');
--insert into usuarios values (uuid_generate_v1(), (select id from empresa), 'admin@cardapiodigital.com', '$2a$12$Nth2.JXetGmy/aFKTVVhhOI50R3TtQlCNoD6QxYevMtqACNvQBHVm', 'Usuário ADMIN', '99999999999', 'SIM');
--insert into permissoes values(nextval('permissoes_id_seq'), 'ROLE_ADMIN');
--insert into usuarios_permissoes (usuario_id, permissao_id) (select(select id from usuarios where login = 'admin@cardapiodigital.com' ) as usuario_id,
--                                                                  (select id from permissoes where permissao_descricao = 'ROLE_ADMIN') as permissao_id);
