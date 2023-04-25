package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.FormaPagamento;
import br.com.cardapioDigital.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento save(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional(readOnly = true)
    public Page<FormaPagamento> findAll(Pageable pageable) {
        return formaPagamentoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public FormaPagamento findById(UUID id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("formaPagamento.naoEncontrada"));
    }

    public void delete(FormaPagamento formaPagamento) {
        formaPagamentoRepository.delete(formaPagamento);
    }
}
