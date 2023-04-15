package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.Usuario;
import br.com.cardapioDigital.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent()) {
            throw new ValidacaoException("usuario.idNaoEncontrado");
        }

        return usuario.get();
    }

}
