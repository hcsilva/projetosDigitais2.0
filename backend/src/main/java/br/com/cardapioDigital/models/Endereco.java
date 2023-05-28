package br.com.cardapioDigital.models;

import br.com.cardapioDigital.dtos.EnderecoDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "ENDERECO")
@EqualsAndHashCode(of = "id")
public class Endereco extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private int numero;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CEP")
    private int cep;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "PAIS")
    private String pais;

    public Endereco() {
        this.pais = "Brazil";
    }

    public EnderecoDto convertEntityToDto() {
        return new ModelMapper().map(this, EnderecoDto.class);
    }

}
