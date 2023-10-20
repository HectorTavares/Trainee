package br.com.cwi.crescer.api.fixture;

import br.com.cwi.crescer.api.domain.Resposta;
import br.com.cwi.crescer.api.domain.Usuario;

import java.util.ArrayList;

import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;

public class RespostaFixture {

    public static Resposta respostaFixture(){

        Resposta resposta = new Resposta();

        Usuario autor = usuarioComumFixture();
        autor.setId(134243423423423342L);

        resposta.setFoto("https://formimagestorage.s3.sa-east-1.amazonaws.com/1635887663332_doggo.jpg");
        resposta.setPergunta(perguntaFixture());
        resposta.setIsAprovado(false);
        resposta.setDescricao("Bem complicado tenta fazer isso");
        resposta.setAutor(autor);
        resposta.setId(1L);
        resposta.setInteracaos(new ArrayList<>());


        return resposta;
    }
}
