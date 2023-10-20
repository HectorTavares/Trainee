package br.com.cwi.crescer.api.fixture;

import br.com.cwi.crescer.api.domain.InteracaoPergunta;
import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;

public class PerguntaComInteracaoPositivaFixture {
    public static Pergunta perguntaComInteracaoPositivaFixture() {

        Usuario usuario = usuarioComumFixture();
        usuario.setId(1L);

        InteracaoPergunta interacaoPergunta = new InteracaoPergunta();
        interacaoPergunta.setId(1L);
        interacaoPergunta.setPergunta(null);
        interacaoPergunta.setAutor(usuario);
        interacaoPergunta.setTipoInteracao(TipoInteracao.POSITIVO);

        List<InteracaoPergunta> interacoesPerguntas = new ArrayList<>();
        interacoesPerguntas.add(interacaoPergunta);
        Pergunta pergunta = perguntaFixture();
        pergunta.setAutor(usuario);
        pergunta.setInteracaoPerguntas(interacoesPerguntas);

        return pergunta;

    }

}
