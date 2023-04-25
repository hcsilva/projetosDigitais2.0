package br.com.cardapioDigital.services;

import br.com.cardapioDigital.dtos.UsuarioDto;
import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.Usuario;
import br.com.cardapioDigital.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Usuario findById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("usuario.idNaoEncontrado"));
    }

    public Usuario save(Usuario usuario) {
        validarNovoUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public String passwordEncoder(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.encode(senha);
    }

    private void validarNovoUsuario(Usuario usuario) {
        if (usuario.getId() != null) {
            return;
        }

        var usuarioEncontrado = usuarioRepository.findByLogin(usuario.getLogin());
        if (usuarioEncontrado != null) {
            throw new ValidacaoException("usuario.usuarioJaCriado");
        }
    }

    @Transactional(readOnly = true)
    public Usuario findUsuarioByLogin(String login){
        return usuarioRepository.findUsuarioByLogin(login);
    }

}
