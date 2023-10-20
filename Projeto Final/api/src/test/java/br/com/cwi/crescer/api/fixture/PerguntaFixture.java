package br.com.cwi.crescer.api.fixture;

import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.Tag;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;

public class PerguntaFixture {

    public static Pergunta perguntaFixture(){

        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setNome("Discord");
        tags.add(tag);

        Pergunta pergunta = new Pergunta();
        pergunta.setAutor(usuarioComumFixture());
        pergunta.setDescricao("Como pegar os cargos pela api do Discord?");
        pergunta.setInteracaoPerguntas(new ArrayList<>());
        pergunta.setFoto("foto.png");
        pergunta.setTags(tags);
        pergunta.setTitulo("Cargos Discord");
        pergunta.setId(1L);
        pergunta.setRespostas(new ArrayList<>());

        return pergunta;
    }

}
