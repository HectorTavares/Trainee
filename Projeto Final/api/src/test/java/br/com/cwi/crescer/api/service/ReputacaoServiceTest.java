package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.InteracaoPergunta;
import br.com.cwi.crescer.api.domain.InteracaoResposta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static br.com.cwi.crescer.api.fixture.InteracaoPerguntaFixture.InteracaoPositivaPerguntaFixture;
import static br.com.cwi.crescer.api.fixture.InteracaoRespostaFixture.interacaoRespostaPositivaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReputacaoServiceTest {

    @InjectMocks
    ReputacaoService tested;

    @Mock
    UsuarioService usuarioService;


    @Test
    public void deveAtualizarReputacaoDoUsuarioCorretamente() {

        Usuario usuario = usuarioComumFixture();

        Double reputacaoEsperada = usuario.getReputacao()+100.00;

        tested.atualizarReputacao(usuario, 100.00);

        verify(usuarioService).atualizarUsuario(usuario);


        assertEquals(reputacaoEsperada, usuario.getReputacao());
    }

    @Test
    public void deveAtualizarAReputacaoDoUsuaioCorretamenteQuandoCriarPergunta() {

        Usuario usuario = usuarioComumFixture();

        Double reputacaoEsperada = usuario.getReputacao()+4.00;


        when(usuarioService.pegarUsuarioPorId(usuario.getId())).thenReturn(usuario);

        tested.criarPergunta(usuario.getId());


        verify(usuarioService).atualizarUsuario(usuario);

        assertEquals(reputacaoEsperada, usuario.getReputacao());
    }

    @Test
    public void deveAtualizarCorretamenteAReputacaoDoUsuarioQuandoExcluirPergunta() {
        Usuario usuario = usuarioComumFixture();

        Double reputacaoEsperada = usuario.getReputacao()-4.00;


        when(usuarioService.pegarUsuarioPorId(usuario.getId())).thenReturn(usuario);

        tested.excluirPergunta(usuario.getId());


        verify(usuarioService).atualizarUsuario(usuario);

        assertEquals(reputacaoEsperada, usuario.getReputacao());
    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaPerguntaEDoAutorDaRespostaCorretamenteQuandoUmaPerguntaForRespondida() {


        Usuario usuarioAutorResposta = usuarioComumFixture();
        Usuario usuarioAutorPergunta = usuarioComumFixture();
        usuarioAutorPergunta.setId(13L);

        Double reputacaoEsperadaAutorResposta = usuarioAutorResposta.getReputacao()+3.00;
        Double reputacaoEsperadaAutorpergunta = usuarioAutorPergunta.getReputacao()+1.00;


        when(usuarioService.pegarUsuarioPorId(usuarioAutorResposta.getId())).thenReturn(usuarioAutorResposta);
        when(usuarioService.pegarUsuarioPorId(usuarioAutorPergunta.getId())).thenReturn(usuarioAutorPergunta);


        tested.responderPergunta(usuarioAutorResposta.getId(),usuarioAutorPergunta.getId());


        verify(usuarioService).atualizarUsuario(usuarioAutorResposta);
        verify(usuarioService).atualizarUsuario(usuarioAutorPergunta);

        assertEquals(reputacaoEsperadaAutorResposta, usuarioAutorResposta.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorPergunta.getReputacao());


    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaPerguntaEDoAutorDaRespostaCorretamenteQuandoARespostaDeUmaPerguntaForExcluida() {

        Usuario usuarioAutorResposta = usuarioComumFixture();
        Usuario usuarioAutorPergunta = usuarioComumFixture();
        usuarioAutorPergunta.setId(13L);

        Double reputacaoEsperadaAutorResposta = usuarioAutorResposta.getReputacao()-3.00;
        Double reputacaoEsperadaAutorpergunta = usuarioAutorPergunta.getReputacao()-1.00;


        when(usuarioService.pegarUsuarioPorId(usuarioAutorResposta.getId())).thenReturn(usuarioAutorResposta);
        when(usuarioService.pegarUsuarioPorId(usuarioAutorPergunta.getId())).thenReturn(usuarioAutorPergunta);


        tested.excluirResposta(usuarioAutorResposta.getId(),usuarioAutorPergunta.getId());


        verify(usuarioService).atualizarUsuario(usuarioAutorResposta);
        verify(usuarioService).atualizarUsuario(usuarioAutorPergunta);

        assertEquals(reputacaoEsperadaAutorResposta, usuarioAutorResposta.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorPergunta.getReputacao());

    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaPerguntaEDoAutorDaInteracaoCorretamenteQuandoAInteracaoComAPerguntaForPositiva() {

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();

        Usuario usuarioAutorInteracao = usuarioComumFixture();
        usuarioAutorInteracao.setId(131313L);

        Usuario usuarioAutorPergunta = usuarioComumFixture();

        Double reputacaoEsperadaAutorInteracao = interacaoPergunta.getAutor().getReputacao()+0.1;
        Double reputacaoEsperadaAutorpergunta = interacaoPergunta.getPergunta().getAutor().getReputacao()+2.00;


        when(usuarioService.pegarUsuarioPorId(interacaoPergunta.getAutor().getId())).thenReturn(usuarioAutorInteracao);
        when(usuarioService.pegarUsuarioPorId(interacaoPergunta.getPergunta().getAutor().getId())).thenReturn(usuarioAutorPergunta);


        tested.interagirPergunta(interacaoPergunta);


        verify(usuarioService).atualizarUsuario(usuarioAutorInteracao);
        verify(usuarioService).atualizarUsuario(usuarioAutorPergunta);

        assertEquals(reputacaoEsperadaAutorInteracao, usuarioAutorInteracao.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorPergunta.getReputacao());

    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaPerguntaEDoAutorDaInteracaoCorretamenteQuandoAInteracaoComAPerguntaForNegativa(){

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();
        interacaoPergunta.setTipoInteracao(TipoInteracao.NEGATIVO);

        Usuario usuarioAutorInteracao = usuarioComumFixture();
        usuarioAutorInteracao.setId(131313L);

        Usuario usuarioAutorPergunta = usuarioComumFixture();

        Double reputacaoEsperadaAutorInteracao = interacaoPergunta.getAutor().getReputacao()+0.1;
        Double reputacaoEsperadaAutorpergunta = interacaoPergunta.getPergunta().getAutor().getReputacao()-2.00;


        when(usuarioService.pegarUsuarioPorId(interacaoPergunta.getAutor().getId())).thenReturn(usuarioAutorInteracao);
        when(usuarioService.pegarUsuarioPorId(interacaoPergunta.getPergunta().getAutor().getId())).thenReturn(usuarioAutorPergunta);


        tested.interagirPergunta(interacaoPergunta);


        verify(usuarioService).atualizarUsuario(usuarioAutorInteracao);
        verify(usuarioService).atualizarUsuario(usuarioAutorPergunta);

        assertEquals(reputacaoEsperadaAutorInteracao, usuarioAutorInteracao.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorPergunta.getReputacao());
    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaPerguntaEDoAutorDaInteracaoCorretamenteQuandoExcluirInteracaoPositivaComAPergunta() {

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();

        Usuario usuarioAutorInteracao = usuarioComumFixture();
        usuarioAutorInteracao.setId(131313L);

        Usuario usuarioAutorPergunta = usuarioComumFixture();

        Double reputacaoEsperadaAutorInteracao = interacaoPergunta.getAutor().getReputacao()-0.1;
        Double reputacaoEsperadaAutorpergunta = interacaoPergunta.getPergunta().getAutor().getReputacao()-2.00;


        when(usuarioService.pegarUsuarioPorId(interacaoPergunta.getAutor().getId())).thenReturn(usuarioAutorInteracao);
        when(usuarioService.pegarUsuarioPorId(interacaoPergunta.getPergunta().getAutor().getId())).thenReturn(usuarioAutorPergunta);


        tested.excluirInteracaoPergunta(interacaoPergunta);


        verify(usuarioService).atualizarUsuario(usuarioAutorInteracao);
        verify(usuarioService).atualizarUsuario(usuarioAutorPergunta);

        assertEquals(reputacaoEsperadaAutorInteracao, usuarioAutorInteracao.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorPergunta.getReputacao());


    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaPerguntaEDoAutorDaInteracaoCorretamenteQuandoExcluirInteracaoNegativaComAPergunta() {


        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();
        interacaoPergunta.setTipoInteracao(TipoInteracao.NEGATIVO);

        Usuario usuarioAutorInteracao = usuarioComumFixture();
        usuarioAutorInteracao.setId(131313L);

        Usuario usuarioAutorPergunta = usuarioComumFixture();

        Double reputacaoEsperadaAutorInteracao = interacaoPergunta.getAutor().getReputacao()-0.1;
        Double reputacaoEsperadaAutorpergunta = interacaoPergunta.getPergunta().getAutor().getReputacao()+2.00;


        when(usuarioService.pegarUsuarioPorId(interacaoPergunta.getAutor().getId())).thenReturn(usuarioAutorInteracao);
        when(usuarioService.pegarUsuarioPorId(interacaoPergunta.getPergunta().getAutor().getId())).thenReturn(usuarioAutorPergunta);


        tested.excluirInteracaoPergunta(interacaoPergunta);


        verify(usuarioService).atualizarUsuario(usuarioAutorInteracao);
        verify(usuarioService).atualizarUsuario(usuarioAutorPergunta);

        assertEquals(reputacaoEsperadaAutorInteracao, usuarioAutorInteracao.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorPergunta.getReputacao());

    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaRespostaEDoAutorDaInteracaoCorretamenteQuandoAInteracaoComARespostaForPositiva() {

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();

        Usuario usuarioAutorInteracao = usuarioComumFixture();
        usuarioAutorInteracao.setId(131313L);

        Usuario usuarioAutorResposta = usuarioComumFixture();

        Double reputacaoEsperadaAutorInteracao = interacaoResposta.getAutor().getReputacao()+0.1;
        Double reputacaoEsperadaAutorpergunta = interacaoResposta.getResposta().getAutor().getReputacao()+1.00;


        when(usuarioService.pegarUsuarioPorId(interacaoResposta.getAutor().getId())).thenReturn(usuarioAutorInteracao);
        when(usuarioService.pegarUsuarioPorId(interacaoResposta.getResposta().getAutor().getId())).thenReturn(usuarioAutorResposta);


        tested.interagirResposta(interacaoResposta);


        verify(usuarioService).atualizarUsuario(usuarioAutorInteracao);
        verify(usuarioService).atualizarUsuario(usuarioAutorResposta);

        assertEquals(reputacaoEsperadaAutorInteracao, usuarioAutorInteracao.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorResposta.getReputacao());


    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaRespostaEDoAutorDaInteracaoCorretamenteQuandoAInteracaoComARespostaForNegativa() {

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();
        interacaoResposta.setTipoInteracao(TipoInteracao.NEGATIVO);

        Usuario usuarioAutorInteracao = usuarioComumFixture();
        usuarioAutorInteracao.setId(131313L);

        Usuario usuarioAutorResposta = usuarioComumFixture();

        Double reputacaoEsperadaAutorInteracao = interacaoResposta.getAutor().getReputacao()+0.1;
        Double reputacaoEsperadaAutorpergunta = interacaoResposta.getResposta().getAutor().getReputacao()-1.00;


        when(usuarioService.pegarUsuarioPorId(interacaoResposta.getAutor().getId())).thenReturn(usuarioAutorInteracao);
        when(usuarioService.pegarUsuarioPorId(interacaoResposta.getResposta().getAutor().getId())).thenReturn(usuarioAutorResposta);


        tested.interagirResposta(interacaoResposta);


        verify(usuarioService).atualizarUsuario(usuarioAutorInteracao);
        verify(usuarioService).atualizarUsuario(usuarioAutorResposta);

        assertEquals(reputacaoEsperadaAutorInteracao, usuarioAutorInteracao.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorResposta.getReputacao());
    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaRespostaEDoAutorDaInteracaoCorretamenteQuandoExcluirInteracaoPositivaComAResposta() {
        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();

        Usuario usuarioAutorInteracao = usuarioComumFixture();
        usuarioAutorInteracao.setId(131313L);

        Usuario usuarioAutorResposta = usuarioComumFixture();

        Double reputacaoEsperadaAutorInteracao = interacaoResposta.getAutor().getReputacao()-0.1;
        Double reputacaoEsperadaAutorpergunta = interacaoResposta.getResposta().getAutor().getReputacao()-1.00;


        when(usuarioService.pegarUsuarioPorId(interacaoResposta.getAutor().getId())).thenReturn(usuarioAutorInteracao);
        when(usuarioService.pegarUsuarioPorId(interacaoResposta.getResposta().getAutor().getId())).thenReturn(usuarioAutorResposta);


        tested.excluirInteracaoResposta(interacaoResposta);


        verify(usuarioService).atualizarUsuario(usuarioAutorInteracao);
        verify(usuarioService).atualizarUsuario(usuarioAutorResposta);

        assertEquals(reputacaoEsperadaAutorInteracao, usuarioAutorInteracao.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorResposta.getReputacao());





    }

    @Test
    public void deveAtualizarAReputacaoDoAutorDaRespostaEDoAutorDaInteracaoCorretamenteQuandoExcluirInteracaoNegativaComAResposta() {

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();
        interacaoResposta.setTipoInteracao(TipoInteracao.NEGATIVO);

        Usuario usuarioAutorInteracao = usuarioComumFixture();
        usuarioAutorInteracao.setId(131313L);

        Usuario usuarioAutorResposta = usuarioComumFixture();

        Double reputacaoEsperadaAutorInteracao = interacaoResposta.getAutor().getReputacao()-0.1;
        Double reputacaoEsperadaAutorpergunta = interacaoResposta.getResposta().getAutor().getReputacao()+1.00;


        when(usuarioService.pegarUsuarioPorId(interacaoResposta.getAutor().getId())).thenReturn(usuarioAutorInteracao);
        when(usuarioService.pegarUsuarioPorId(interacaoResposta.getResposta().getAutor().getId())).thenReturn(usuarioAutorResposta);


        tested.excluirInteracaoResposta(interacaoResposta);


        verify(usuarioService).atualizarUsuario(usuarioAutorInteracao);
        verify(usuarioService).atualizarUsuario(usuarioAutorResposta);

        assertEquals(reputacaoEsperadaAutorInteracao, usuarioAutorInteracao.getReputacao());
        assertEquals(reputacaoEsperadaAutorpergunta, usuarioAutorResposta.getReputacao());
    }
}