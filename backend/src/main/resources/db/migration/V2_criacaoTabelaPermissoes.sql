create table permissoes(
  id serial not null,
  permissao_descricao varchar(255) not null,
  primary key (id),
  unique (permissao_descricao)
);

create table usuarios_permissoes(
    usuario_id integer NOT NULL,
    permissao_id integer NOT NULL
--    CONSTRAINT fk_permissoes_usuarios_permissoes FOREIGN KEY (permissao_id)
--        REFERENCES permissoes (id),
--    CONSTRAINT fk_usuarios_usuarios_permissoes FOREIGN KEY (usuario_id)
--        REFERENCES usuarios(id)
);