package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import br.com.cwi.crescer.api.representation.notificacao.NotificacaoResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.NotificacaoFixture.notificacaoFixture;
import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.RespostaFixture.respostaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificacaoServiceTest {

    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    NotificacaoService notificacaoService;

    @Mock
    NotificacaoRepository repository;

    @Mock
    PerguntaService perguntaService;

    @Mock
    UsuarioService usuarioService;

    @Mock
    RespostaService respostaService;

    @Test
    public void deveCriarNotificacaoCorretamente() {
        Usuario usuario = usuarioComumFixture();
        Pergunta pergunta = perguntaFixture();
        when(usuarioService.pegarUsuarioPorId(usuario.getId())).thenReturn(usuario);
        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);

        Notificacao notificacao = notificacaoService.criarNotificacao(pergunta.getId(), usuario.getId());

        Assert.assertEquals(pergunta.getTitulo(), notificacao.getPergunta().getTitulo());
    }

    @Test
    public void deveVerificarSeUsuarioDestinatarioNaoEOAutorDaAcao() {
        Usuario usuarioDestinatario = usuarioComumFixture();
        Usuario autorAcao = usuarioComumFixture();
        autorAcao.setId(2L);
        Pergunta pergunta = perguntaFixture();
        Notificacao notificacao = new Notificacao();
        when(repository.pegarNotificacaoPorPerguntaIdEDestinatarioIdETipoNotificacao(pergunta.getId(), usuarioDestinatario.getId(), TipoNotificacao.RESPOSTA)).thenReturn(notificacao);

        notificacaoService.notificarResposta(pergunta.getId(), usuarioDestinatario.getId(), autorAcao.getId());

        verify(repository, times(1)).pegarNotificacaoPorPerguntaIdEDestinatarioIdETipoNotificacao(any(), any(), any());

    }

    @Test
    public void deveVerificarSeANotificacaoRespostaNaoExisteECriarNovaNotificacao() {
        Usuario usuarioDestinatario = usuarioComumFixture();
        usuarioDestinatario.setId(1L);
        Usuario autorAcao = usuarioComumFixture();
        autorAcao.setId(2L);

        Pergunta pergunta = perguntaFixture();
        when(repository.pegarNotificacaoPorPerguntaIdEDestinatarioIdETipoNotificacao(pergunta.getId(), usuarioDestinatario.getId(), TipoNotificacao.RESPOSTA)).thenReturn(null);

        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);

        notificacaoService.notificarResposta(pergunta.getId(), usuarioDestinatario.getId(), autorAcao.getId());

        verify(repository, times(1)).save(any());

    }

    @Test
    public void deveCriarNotificacaoDeInteracaoDaPerguntaCorretamente() {
        Usuario usuarioDestinatario = usuarioComumFixture();

        Pergunta pergunta = perguntaFixture();

        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);
        notificacaoService.notificarInteracaoPergunta(pergunta.getId(), usuarioDestinatario.getId());
        verify(repository, times(1)).save(any());

    }

    @Test
    public void deveCriarNotificacaoDeInteracaoDaRespostaCorretamente() {
        Usuario usuarioDestinatario = usuarioComumFixture();

        Resposta resposta = respostaFixture();
        Pergunta pergunta = perguntaFixture();

        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);
        when(respostaService.pegarRespostaPorId(resposta.getId())).thenReturn(resposta);

        notificacaoService.notificarInteracaoResposta(pergunta.getId(), usuarioDestinatario.getId(), resposta.getId());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void deveExcluirComSucessoUmaNotificacaoVisualizada() {
        Notificacao notificacao = notificacaoFixture();
        Usuario usuario = usuarioComumFixture();
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        when(repository.findNotificacaoById(notificacao.getId())).thenReturn(notificacao);
        notificacaoService.visualizarNotificacaoPorId(notificacao.getId(), "token");
        verify(repository, times(1)).deleteById(notificacao.getId());
    }

    @Test
    public void deveExcluirComSucessoTodasNotificacoesDoUsuario() {
        Usuario usuario = usuarioComumFixture();
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        notificacaoService.visualizarTodasNotificacoesPorIdDestinatario("token");
        verify(repository, times(1)).deleteByDestinatarioId(any());
    }

    @Test(expected = UnauthorizedException.class)
    public void deveRetornarErroSeUsuarioNaoForDestinatarioDaNotificacao() {
        Usuario usuario = usuarioComumFixture();
        usuario.setId(1L);
        Notificacao notificacao = notificacaoFixture();
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        when(repository.findNotificacaoById(notificacao.getId())).thenReturn(notificacao);

        notificacaoService.isUsuarioDestinatarioNotificacao(notificacao.getId(), "token");
    }

    @Test
    public void deveRetornarTodasNotificacoesCorretamente() {
        Usuario usuario = usuarioComumFixture();
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);

        Notificacao notificacao = notificacaoFixture();
        List<Notificacao> notificacaoList = new ArrayList<>();
        notificacaoList.add(notificacao);

        when(repository.pegarNotificacoesUsuario(usuario.getId())).thenReturn(notificacaoList);
        List<NotificacaoResponse> retorno = notificacaoService.pegarTodasNotificacoes("token");

        Assert.assertEquals(notificacaoList.size(), retorno.size());
    }
}
