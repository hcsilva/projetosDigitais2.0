package br.com.cardapioDigital.dtos;

import br.com.cardapioDigital.models.ItemCategoria;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemCategoriaDto {

    private static ModelMapper modelMapper = new ModelMapper();

    private Long id;

    @NotNull(message = "{itemCategoria.empresa.campoObrigatorio}")
    private Long empresaId;

    @NotNull(message = "{itemCategoria.categoria.campoObrigatorio}")
    private Long categoriaId;

    @NotBlank(message = "{itemCategoria.descricaoDetalhada.campoObrigatorio}")
    private String descricaoDetalhada;

    @Schema(description = "Descrição detalhada do prato")
    @NotBlank(message = "{itemCategoria.descricaoSimples.campoObrigatorio}")
    private String descricaoSimples;

    @Schema(description = "Descrição Simples do prato")
    @NotNull(message = "{itemCategoria.preco.campoObrigatorio}")
    private BigDecimal preco;

    private byte[] foto;
    private BigDecimal precoMiniPrato;
    private Integer serverQuantidadePessoas;
    private boolean semLactose;
    private boolean vegetariano;
    private boolean semGluten;
    private boolean vegano;
    private boolean destaque;

    public ItemCategoria convertDTOToEntity() {
        return modelMapper.map(this, ItemCategoria.class);
    }
}


