package br.com.cwi.crescer.melevaai.representation;

import br.com.cwi.crescer.melevaai.domain.Categoria;
import br.com.cwi.crescer.melevaai.domain.Cor;
import br.com.cwi.crescer.melevaai.domain.Marca;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VeiculoCompletoRequest {
    @NotBlank
    @NotEmpty
    private String placa;
    @NotBlank
    @NotEmpty
    private Marca marca;
    @NotBlank
    @NotEmpty
    private String modelo;
    @NotBlank
    @NotNull
    private int ano;
    @NotBlank
    @NotEmpty
    private Cor cor;
    @NotBlank
    @NotEmpty
    private String foto;
    @NotBlank
    @NotEmpty
    private Categoria categoria;
    @NotBlank
    @NotNull
    private int quantidadeLugares;
    @NotBlank
    @NotNull
    private String cpfProprietario;
}
