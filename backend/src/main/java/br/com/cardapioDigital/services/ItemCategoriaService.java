package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.ItemCategoria;
import br.com.cardapioDigital.repositories.ItemCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemCategoriaService {

    @Autowired
    private ItemCategoriaRepository itemCategoriaRepository;

    public ItemCategoria save(ItemCategoria itemCategoria) {
        return itemCategoriaRepository.save(itemCategoria);
    }

    @Transactional(readOnly = true)
    public Page<ItemCategoria> findAll(Pageable pageable) {
        return itemCategoriaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ItemCategoria findById(Long id) {
        return itemCategoriaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("itemCategoria.naoEncontrada"));
    }

    public void delete(ItemCategoria itemCategoria) {
        itemCategoriaRepository.delete(itemCategoria);
    }
}
