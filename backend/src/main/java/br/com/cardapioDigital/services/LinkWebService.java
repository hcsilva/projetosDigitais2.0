package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.LinkWeb;
import br.com.cardapioDigital.repositories.LinkWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class LinkWebService {

    @Autowired
    private LinkWebRepository linkRepository;

    public LinkWeb save(LinkWeb link) {
        return linkRepository.save(link);
    }

    @Transactional(readOnly = true)
    public Page<LinkWeb> findAll(Pageable pageable) {
        return linkRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public LinkWeb findById(UUID id) {
        return linkRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("empresa.naoEncontrada"));
    }

    public void delete(LinkWeb link) {
        linkRepository.delete(link);
    }

}
