package br.com.cwi.crescer.api.representation.interacao.resposta;

import br.com.cwi.crescer.api.domain.TipoInteracao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InteracaoRespostaResponse {
    private Long id;

    private TipoInteracao tipoInteracao;

    private String emailAutor;
}
