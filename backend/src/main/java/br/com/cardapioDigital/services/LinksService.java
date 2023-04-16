package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.Links;
import br.com.cardapioDigital.repositories.LinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LinksService {

    @Autowired
    private LinksRepository linksRepository;

    public Links save(Links links) {
        return linksRepository.save(links);
    }

    @Transactional(readOnly = true)
    public Page<Links> findAll(Pageable pageable) {
        return linksRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Links findById(Long id) {
        return linksRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("empresa.naoEncontrada"));
    }

    public void delete(Links links) {
        linksRepository.delete(links);
    }

}
