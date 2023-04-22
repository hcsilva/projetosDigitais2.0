package br.com.cardapioDigital.models;

import br.com.cardapioDigital.dtos.EmpresaDto;
import br.com.cardapioDigital.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "EMPRESA")
@EqualsAndHashCode(of = "id")
public class Empresa extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RAZAO_SOCIAL", length = 150)
    private String razaoSocial;

    @Column(name = "CNPJ")
    private String cnpj;

//    @Column(name = "LOGO")
//    @Lob
//    private byte[] logo;
//
//    @Column(name = "IMAGEM_CAPA")
//    @Lob
//    private byte[] imagemCapa;
//
//    @Column(name = "DESCRICAO", length = 300)
//    private String descricao;
//
//    @Column(name = "SITE", length = 100)
//    private String site;
//
//    @Column(name = "INSTAGRAM", length = 100)
//    private String instagram;
//
//    @Column(name = "FACEBOOK", length = 100)
//    private String facebook;
//
//    @Column(name = "WHATSAPP", length = 20)
//    private String whatsapp;

    @Column(name = "TELEFONE_CONTATO", length = 100)
    private String telefoneContato;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "IDIOMA", length = 5)
    private String idioma;

    @Column(name = "FUSO", length = 10)
    private String fusoHorario;

    public Empresa() {
        this.idioma = "pt-BR";
        this.fusoHorario = "Z";
    }

    public EmpresaDto convertEntityToDto() {
        return new ModelMapper().map(this, EmpresaDto.class);
    }
}
