package br.com.cwi.crescer.api.representation.notificacao;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoResponse {

    private Long idNotificacao;

    private Long idPergunta;

    private String mensagem;


}
