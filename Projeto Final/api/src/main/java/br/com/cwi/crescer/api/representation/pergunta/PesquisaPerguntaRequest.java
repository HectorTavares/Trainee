package br.com.cwi.crescer.api.representation.pergunta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PesquisaPerguntaRequest {

    private String busca;

    private List<Long> tags;

}
