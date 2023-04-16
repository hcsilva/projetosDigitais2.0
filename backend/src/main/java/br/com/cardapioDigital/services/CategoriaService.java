package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.Categoria;
import br.com.cardapioDigital.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Transactional(readOnly = true)
    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(
                        () -> new ValidacaoException("categoria.naoEncontrada"));
    }

    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}
