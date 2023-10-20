package br.com.cwi.crescer.api.representation.resposta;

import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RespostaResponse {

    private Long id;

    private UsuarioResponse autor;

    private String descricao;

    private String foto;

    private Long idPergunta;

    private Boolean isAprovado;

    private LocalDateTime dataHora;

    private Integer relevancia;

}
