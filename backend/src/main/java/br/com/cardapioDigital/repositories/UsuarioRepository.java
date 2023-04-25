package br.com.cardapioDigital.repositories;

import br.com.cardapioDigital.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    UserDetails findByLogin(String login);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.permissoes WHERE u.login = ?1")
    UserDetails findUserByUsernameWithPermissoes(String username);

    @Query("SELECT u FROM Usuario u WHERE u.login = ?1")
    Usuario findUsuarioByLogin(String login);
}
