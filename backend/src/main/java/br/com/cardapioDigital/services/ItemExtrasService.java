package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.ItemExtras;
import br.com.cardapioDigital.repositories.ItemExtrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ItemExtrasService {

    @Autowired
    private ItemExtrasRepository itemExtrasRepository;

    @Transactional
    public ItemExtras save(ItemExtras itemExtras) {
        return itemExtrasRepository.save(itemExtras);
    }

    @Transactional(readOnly = true)
    public Page<ItemExtras> findAll(Pageable pageable) {
        return itemExtrasRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ItemExtras findById(UUID id) {
        return itemExtrasRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("empresa.naoEncontrada"));
    }

    @Transactional
    public void delete(ItemExtras itemExtras) {
        itemExtrasRepository.delete(itemExtras);
    }
}
