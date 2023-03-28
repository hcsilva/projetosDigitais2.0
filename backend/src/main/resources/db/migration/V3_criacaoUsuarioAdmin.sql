insert into usuarios values (nextval('usuarios_id_seq'), 'admin@cardapiodigital.com', '$2a$12$Nth2.JXetGmy/aFKTVVhhOI50R3TtQlCNoD6QxYevMtqACNvQBHVm');
insert into permissoes values(nextval('permissoes_id_seq'), 'ROLE_ADMIN');
insert into usuarios_permissoes (usuario_id, permissao_id) (select(select id from usuarios where login = 'admin@cardapiodigital.com' ) as usuario_id,
                                                                  (select id from permissoes where permissao_descricao = 'ROLE_ADMIN') as permissao_id);