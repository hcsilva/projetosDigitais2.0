package br.com.cardapioDigital.models;

import br.com.cardapioDigital.dtos.UsuarioDto;
import br.com.cardapioDigital.enums.SimNaoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USUARIOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_EMPRESA", nullable = false)
    private UUID idEmpresa;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "ATIVO")
    private SimNaoEnum usuarioAtivo;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USUARIOS_PERMISSOES",
            joinColumns = @JoinColumn(name = "USUARIO_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSAO_ID"))
    private List<Permissao> permissoes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Permissao permissao: permissoes) {
            authorities.add(new SimpleGrantedAuthority(permissao.getName().toString()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return SimNaoEnum.SIM.equals(this.usuarioAtivo);
    }

    public UsuarioDto convertEntityToDto() {
        return new ModelMapper().map(this, UsuarioDto.class);
    }
}
