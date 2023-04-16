package br.com.cardapioDigital.services;

import br.com.cardapioDigital.dtos.CepResponse;
import br.com.cardapioDigital.dtos.EnderecoDto;
import br.com.cardapioDigital.exceptions.ValidacaoException;
import br.com.cardapioDigital.models.Endereco;
import br.com.cardapioDigital.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class EnderecoService {
    private static final String URL_BASE_VIA_CEP = "https://viacep.com.br/ws/%s/json/";

    @Autowired
    private EnderecoRepository enderecoRepository;


    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Transactional(readOnly = true)
    public Page<Endereco> findAll(Pageable pageable) {
        return enderecoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(
                        () -> new ValidacaoException("endereco.naoEncontrada"));
    }

    public void delete(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }

    public EnderecoDto findByCep(int cep) {
        validateCep(cep);

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL_BASE_VIA_CEP, cep);
        CepResponse response = restTemplate.getForObject(url, CepResponse.class);

        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setBairro(response.getBairro());
        enderecoDto.setComplemento(response.getComplemento());
        enderecoDto.setEstado(response.getUf());
        enderecoDto.setCidade(response.getLocalidade());
        enderecoDto.setLogradouro(response.getLogradouro());
        enderecoDto.setCep(cep);

        return enderecoDto;
    }

    private void validateCep(Integer cep) {
        if (null == cep) {
            throw new ValidacaoException("endereco.cep.cepDeveEstarPreenchido");
        }

        if ( 8 != cep.toString().length()) {
            throw new ValidacaoException("endereco.cep.tamanhoMaximoExcedido");
        }
    }

}
