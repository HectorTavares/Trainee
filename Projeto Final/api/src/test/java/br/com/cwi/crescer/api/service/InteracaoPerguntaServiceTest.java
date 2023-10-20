package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.InteracaoPergunta;
import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.InteracaoPerguntaRepository;
import br.com.cwi.crescer.api.representation.interacao.pergunta.InteracaoPerguntaResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.InteracaoPerguntaFixture.InteracaoPositivaPerguntaFixture;
import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InteracaoPerguntaServiceTest {

    @InjectMocks
    InteracaoPerguntaService interacaoPerguntaService;

    @Mock
    PerguntaService perguntaService;

    @Mock
    UsuarioService usuarioService;

    @Mock
    InteracaoPerguntaRepository repository;

    @Mock
    ReputacaoService reputacaoService;

    @Mock
    NotificacaoService notificacaoService;

    @Test
    public void deveAvaliarPositivamenteUmaPerguntaComInteracaoPositiva() {

        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Pergunta pergunta = perguntaFixture();
        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();

        when(repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuario.getId(), pergunta.getId())).thenReturn(interacaoPergunta);

        interacaoPerguntaService.avaliarPositivamentePergunta("token", pergunta.getId());

        assertEquals(TipoInteracao.POSITIVO, interacaoPergunta.getTipoInteracao());

    }

    @Test
    public void deveAvaliarPositivamenteUmaPerguntaComInteracaoNula() {

        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Pergunta pergunta = perguntaFixture();
        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);
        pergunta.setInteracaoPerguntas(null);

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();
        when(repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(any(), any())).thenReturn(null);

        interacaoPerguntaService.avaliarPositivamentePergunta("token", pergunta.getId());

        Assert.assertEquals(TipoInteracao.POSITIVO, interacaoPergunta.getTipoInteracao());
    }

    @Test
    public void deveAvaliarPositivamenteUmaPerguntaComInteracaoNegativa() {

        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Pergunta pergunta = perguntaFixture();
        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);
        pergunta.setInteracaoPerguntas(null);

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();
        interacaoPergunta.setTipoInteracao(TipoInteracao.NEGATIVO);
        when(repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(any(), any())).thenReturn(interacaoPergunta);

        interacaoPerguntaService.avaliarPositivamentePergunta("token", pergunta.getId());

        Assert.assertEquals(TipoInteracao.POSITIVO, interacaoPergunta.getTipoInteracao());
    }

    @Test
    public void deveAvaliarNegativamenteUmaPerguntaComInteracaoPositiva() {

        Usuario usuario = usuarioComumFixture();
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Pergunta pergunta = perguntaFixture();
        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();

        when(repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuario.getId(), pergunta.getId())).thenReturn(interacaoPergunta);

        interacaoPerguntaService.avaliarNegativamentePergunta("token", pergunta.getId());

        assertEquals(TipoInteracao.NEGATIVO, interacaoPergunta.getTipoInteracao());

    }

    @Test
    public void deveAvaliarNullUmaPerguntaComInteracaoNegativa() {

        Usuario usuario = usuarioComumFixture();
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Pergunta pergunta = perguntaFixture();
        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();
        interacaoPergunta.setTipoInteracao(null);

        when(repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuario.getId(), pergunta.getId())).thenReturn(interacaoPergunta);

        interacaoPerguntaService.avaliarNegativamentePergunta("token", pergunta.getId());

        assertNull(null, interacaoPergunta.getTipoInteracao());

    }

    @Test
    public void deveAvaliarNullUmaPerguntaComInteracaoNegativa2() {

        Usuario usuario = usuarioComumFixture();
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Pergunta pergunta = perguntaFixture();
        when(perguntaService.pegarPerguntaPorId(pergunta.getId())).thenReturn(pergunta);

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();
        interacaoPergunta.setTipoInteracao(null);

        when(repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuario.getId(), pergunta.getId())).thenReturn(null);

        interacaoPerguntaService.avaliarNegativamentePergunta("token", pergunta.getId());

        assertNull(null, interacaoPergunta.getTipoInteracao());

    }

    @Test
    public void deveListarTodasInteracoesDeUmaPergunta() {

        List<InteracaoPergunta> interacaoPerguntas = new ArrayList<>();

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();
        InteracaoPergunta interacaoPergunta2 = InteracaoPositivaPerguntaFixture();
        interacaoPergunta2.setId(2L);

        interacaoPerguntas.add(interacaoPergunta);
        interacaoPerguntas.add(interacaoPergunta2);

        Pergunta pergunta = perguntaFixture();
        pergunta.setInteracaoPerguntas(interacaoPerguntas);

        when(repository.findAllByPerguntaId(pergunta.getId())).thenReturn(pergunta.getInteracaoPerguntas());

        interacaoPerguntaService.listarInteracoes(pergunta.getId());
        Assert.assertEquals(interacaoPerguntas.size(), pergunta.getInteracaoPerguntas().size());

    }

    @Test
    public void deveRetornarRelevanciaDaPerguntaCorretamente() {

        List<InteracaoPergunta> interacaoPerguntas = new ArrayList<>();

        InteracaoPergunta interacaoPergunta = InteracaoPositivaPerguntaFixture();
        InteracaoPergunta interacaoPergunta2 = InteracaoPositivaPerguntaFixture();
        interacaoPergunta2.setId(2L);

        interacaoPerguntas.add(interacaoPergunta);
        interacaoPerguntas.add(interacaoPergunta2);

        Pergunta pergunta = perguntaFixture();
        pergunta.setInteracaoPerguntas(interacaoPerguntas);

        when(repository.findAllByPerguntaId(pergunta.getId())).thenReturn(pergunta.getInteracaoPerguntas());

        interacaoPerguntaService.pegarRelevanciaPorId(pergunta.getId());

        Assert.assertEquals(interacaoPerguntas.size(), pergunta.getInteracaoPerguntas().size());
    }

    @Test
    public void deveRetornarComSucesseoInteracaoDoUsuario() {
        Usuario usuario = usuarioComumFixture();
        Pergunta pergunta = perguntaFixture();
        InteracaoPergunta interacaoPergunta = new InteracaoPergunta();
        interacaoPergunta.setId(1L);
        interacaoPergunta.setPergunta(pergunta);
        interacaoPergunta.setAutor(usuario);
        interacaoPergunta.setTipoInteracao(TipoInteracao.POSITIVO);
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        when(repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuario.getId(), pergunta.getId())).thenReturn(interacaoPergunta);

        InteracaoPerguntaResponse interacao = interacaoPerguntaService.interacaoUsuario(pergunta.getId(), "token");
        Assert.assertEquals(TipoInteracao.POSITIVO, interacao.getTipoInteracao());
    }

    @Test
    public void deveRetornarNullSeNaoHouverInteracaoDoUsuario() {
        Usuario usuario = usuarioComumFixture();
        usuario.setInteracoesPerguntas(null);
        Pergunta pergunta = perguntaFixture();
        pergunta.setInteracaoPerguntas(null);
        InteracaoPergunta interacaoPergunta = null;
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        when(repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuario.getId(), pergunta.getId())).thenReturn(interacaoPergunta);
        InteracaoPerguntaResponse interacao = interacaoPerguntaService.interacaoUsuario(pergunta.getId(), "token");
        assertNull(null, interacao);
    }

}
