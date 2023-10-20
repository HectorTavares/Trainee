package br.com.cwi.crescer.api.fixture;

import br.com.cwi.crescer.api.domain.*;

import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.RespostaFixture.respostaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;

public class NotificacaoFixture {


    public static Notificacao notificacaoFixture() {

        Usuario usuario = usuarioComumFixture();
        Pergunta pergunta = perguntaFixture();
        Resposta resposta = respostaFixture();

        Notificacao notificacao = new Notificacao();
        notificacao.setId(1L);
        notificacao.setDestinatario(usuario);
        notificacao.setPergunta(pergunta);
        notificacao.setResposta(resposta);
        notificacao.setTipoNotificao(TipoNotificacao.RESPOSTA);
        notificacao.setMensagem("Mensagem de teste da fixture");

        return notificacao;
    }
}
