package br.com.cwi.crescer.api.fixture;

import br.com.cwi.crescer.api.domain.InteracaoPergunta;
import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;

import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;

public class InteracaoPerguntaFixture {

    public static InteracaoPergunta InteracaoPositivaPerguntaFixture(){

        Usuario autor = usuarioComumFixture();
        autor.setId(131313L);

        InteracaoPergunta interacaoPergunta = new InteracaoPergunta();

        interacaoPergunta.setTipoInteracao(TipoInteracao.POSITIVO);
        interacaoPergunta.setPergunta(perguntaFixture());
        interacaoPergunta.setId(1L);
        interacaoPergunta.setAutor(autor);



        return interacaoPergunta;
    }


}
