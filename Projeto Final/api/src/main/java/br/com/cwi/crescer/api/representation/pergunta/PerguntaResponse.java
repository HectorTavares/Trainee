package br.com.cwi.crescer.api.representation.pergunta;


import br.com.cwi.crescer.api.representation.tag.TagResponse;
import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class PerguntaResponse {

    private Long id;

    private UsuarioResponse autor;

    private String titulo;

    private String descricao;

    private String foto;

    private List<TagResponse> tags;

    private LocalDateTime dataHora;

    private Integer contadorRespostas;

    private Integer contadorRelevancia;

}
