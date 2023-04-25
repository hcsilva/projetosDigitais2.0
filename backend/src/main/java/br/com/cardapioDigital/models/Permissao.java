package br.com.cardapioDigital.models;

import br.com.cardapioDigital.enums.PermissaoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Table(name = "PERMISSOES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Permissao implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERMISSAO_DESCRICAO", nullable = false, unique = true)
    private PermissaoEnum name;

    @Override
    public String getAuthority() {
        return this.name.toString();
    }
}
