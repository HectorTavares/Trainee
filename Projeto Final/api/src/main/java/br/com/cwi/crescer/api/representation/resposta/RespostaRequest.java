package br.com.cwi.crescer.api.representation.resposta;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RespostaRequest {


    @NotNull
    private long idPergunta;

    @NotBlank
    private String descricao;

    @NotBlank
    private String foto;

}
