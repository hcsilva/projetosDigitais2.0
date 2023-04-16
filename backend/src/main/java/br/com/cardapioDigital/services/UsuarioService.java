package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.Usuario;
import br.com.cardapioDigital.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
       return usuarioRepository.findById(id)
               .orElseThrow(() -> new ValidacaoException("usuario.idNaoEncontrado"));
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

}
