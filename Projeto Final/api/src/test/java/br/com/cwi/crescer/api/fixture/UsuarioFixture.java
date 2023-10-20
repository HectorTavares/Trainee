package br.com.cwi.crescer.api.fixture;

import br.com.cwi.crescer.api.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UsuarioFixture {

    public static Usuario usuarioComumFixture(){
        Usuario usuario = new Usuario();

        List<Pergunta> perguntas = new ArrayList<>();
        List<Resposta> respostas = new ArrayList<>();
        List<InteracaoPergunta> interacoesPerguntas = new ArrayList<>();
        List<InteracaoResposta> interacoesRespostas = new ArrayList<>();

        usuario.setId(123456789L);
        usuario.setUsername("Tibiritã Soares");
        usuario.setEmail("Tibi.s@email.com");
        usuario.setAvatar("123456789");
        usuario.setReputacao(10.0);
        usuario.setIsMonitor(false);
        usuario.setPerguntas(perguntas);
        usuario.setRespostas(respostas);
        usuario.setInteracoesPerguntas(interacoesPerguntas);
        usuario.setInteracoesRespostas(interacoesRespostas);

        return usuario;
    }

    public static Usuario usuarioMonitorFixture(){
        Usuario usuario = new Usuario();

        List<Pergunta> perguntas = new ArrayList<>();
        List<Resposta> respostas = new ArrayList<>();
        List<InteracaoPergunta> interacoesPerguntas = new ArrayList<>();
        List<InteracaoResposta> interacoesRespostas = new ArrayList<>();

        usuario.setId(123456789L);
        usuario.setUsername("Marineu Saldanha");
        usuario.setEmail("Marineu.s@email.com");
        usuario.setAvatar("123456789");
        usuario.setReputacao(10.0);
        usuario.setIsMonitor(true);
        usuario.setPerguntas(perguntas);
        usuario.setRespostas(respostas);
        usuario.setInteracoesPerguntas(interacoesPerguntas);
        usuario.setInteracoesRespostas(interacoesRespostas);

        return usuario;
    }

}
