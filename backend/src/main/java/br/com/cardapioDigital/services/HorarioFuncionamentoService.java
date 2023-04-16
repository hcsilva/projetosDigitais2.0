package br.com.cardapioDigital.services;

import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.HorarioFuncionamento;
import br.com.cardapioDigital.repositories.HorarioFuncionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HorarioFuncionamentoService {

    @Autowired
    private HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    public HorarioFuncionamento save(HorarioFuncionamento HorarioFuncionamento) {
        return horarioFuncionamentoRepository.save(HorarioFuncionamento);
    }

    @Transactional(readOnly = true)
    public Page<HorarioFuncionamento> findAll(Pageable pageable) {
        return horarioFuncionamentoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public HorarioFuncionamento findById(Long id) {
        return horarioFuncionamentoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("horarioFuncionamento.naoEncontrada"));
    }

    public void delete(HorarioFuncionamento horarioFuncionamento) {
        horarioFuncionamentoRepository.delete(horarioFuncionamento);
    }
}
