package br.com.cwi.crescer.api.representation.resposta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarRespostaRequest {

    private Long idResposta;
    private String descricao;
    private String foto;

}
