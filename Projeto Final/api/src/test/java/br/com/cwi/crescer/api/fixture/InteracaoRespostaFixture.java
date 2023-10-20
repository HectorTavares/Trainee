package br.com.cwi.crescer.api.fixture;

import br.com.cwi.crescer.api.domain.InteracaoResposta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;

import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.RespostaFixture.respostaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;

public class InteracaoRespostaFixture {

    public static InteracaoResposta interacaoRespostaPositivaFixture(){
        InteracaoResposta interacaoResposta = new InteracaoResposta();

        Usuario autor = usuarioComumFixture();


        interacaoResposta.setId(1L);
        interacaoResposta.setAutor(autor);
        interacaoResposta.setResposta(respostaFixture());
        interacaoResposta.setTipoInteracao(TipoInteracao.POSITIVO);


        return interacaoResposta;
    }


}
