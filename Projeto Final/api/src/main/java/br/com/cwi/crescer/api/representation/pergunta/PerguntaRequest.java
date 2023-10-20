package br.com.cwi.crescer.api.representation.pergunta;


import br.com.cwi.crescer.api.representation.tag.TagRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerguntaRequest {

    private String titulo;

    private String descricao;

    private String foto;

    private List<TagRequest> tags;

}
