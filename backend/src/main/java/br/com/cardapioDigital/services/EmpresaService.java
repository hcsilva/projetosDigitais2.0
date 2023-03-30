package br.com.cardapioDigital.services;

import br.com.cardapioDigital.models.Empresa;
import br.com.cardapioDigital.repositories.EmpresaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Transactional(readOnly = true)
    public Page<Empresa> findAll(Pageable pageable) {
        return empresaRepository.findAll(pageable);
    }

/*    @Transactional(readOnly = true)
    public Empresa findById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("empresa.naoEncontrada")
                );
    }*/
    public void delete(Empresa empresa) throws DataIntegrityViolationException {
        empresaRepository.delete(empresa);
    }
}
