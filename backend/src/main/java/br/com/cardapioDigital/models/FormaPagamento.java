package br.com.cardapioDigital.models;

import br.com.cardapioDigital.dtos.FormaPagamentoDto;
import br.com.cardapioDigital.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FORMA_PAGAMENTO")
@EqualsAndHashCode(of = "id")
public class FormaPagamento extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @Column(name = "DINHEIRO")
    private boolean dinheiro;

    @Column(name = "CARTAO_CREDITO")
    private boolean cartaoCredito;

    @Column(name = "CARTAO_DEBITO")
    private boolean cartaoDebito;

    @Column(name = "VALE_REFEICAO")
    private boolean valeRefeicao;

    @Column(name = "VISIVEL")
    private boolean visivel;

    public FormaPagamentoDto convertEntityToDto() {
        return new ModelMapper().map(this, FormaPagamentoDto.class);
    }


}
