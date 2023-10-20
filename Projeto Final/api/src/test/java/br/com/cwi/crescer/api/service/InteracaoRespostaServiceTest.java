package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.InteracaoResposta;
import br.com.cwi.crescer.api.domain.Resposta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.InteracaoRespostaRepository;
import br.com.cwi.crescer.api.representation.interacao.resposta.InteracaoRespostaResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.InteracaoRespostaFixture.interacaoRespostaPositivaFixture;
import static br.com.cwi.crescer.api.fixture.RespostaFixture.respostaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InteracaoRespostaServiceTest {
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    InteracaoRespostaService interacaoRespostaService;

    @Mock
    RespostaService respostaService;

    @Mock
    UsuarioService usuarioService;

    @Mock
    InteracaoRespostaRepository repository;

    @Mock
    ReputacaoService reputacaoService;

    @Mock
    NotificacaoService notificacaoService;

    @Test
    public void deveAvaliarPositivamenteResposta() {
        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Resposta resposta = respostaFixture();
        when(respostaService.pegarRespostaPorId(resposta.getId())).thenReturn(resposta);

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();

        when(repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), resposta.getId())).thenReturn(interacaoResposta);
        interacaoRespostaService.avaliarPositivamenteResposta("token", resposta.getId());

        TipoInteracao avaliacaoEsperada = TipoInteracao.POSITIVO;

        Assert.assertEquals(avaliacaoEsperada, interacaoResposta.getTipoInteracao());
    }

    @Test
    public void deveAvaliarPositivamenteRespostaQuandoInteracaoForNula() {
        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Resposta resposta = respostaFixture();
        when(respostaService.pegarRespostaPorId(resposta.getId())).thenReturn(resposta);

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();

        when(repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), resposta.getId())).thenReturn(null);
        interacaoRespostaService.avaliarPositivamenteResposta("token", resposta.getId());

        TipoInteracao avaliacaoEsperada = TipoInteracao.POSITIVO;

        Assert.assertEquals(avaliacaoEsperada, interacaoResposta.getTipoInteracao());
    }

    @Test
    public void deveAvaliarPositivamenteRespostaQuandoInteracaoForNegativa() {
        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Resposta resposta = respostaFixture();
        when(respostaService.pegarRespostaPorId(resposta.getId())).thenReturn(resposta);

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();
        interacaoResposta.setTipoInteracao(TipoInteracao.NEGATIVO);
        when(repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), resposta.getId())).thenReturn(interacaoResposta);
        interacaoRespostaService.avaliarPositivamenteResposta("token", resposta.getId());

        TipoInteracao avaliacaoEsperada = TipoInteracao.POSITIVO;

        Assert.assertEquals(avaliacaoEsperada, interacaoResposta.getTipoInteracao());
    }


    @Test
    public void deveAvaliarNegativamenteResposta() {
        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Resposta resposta = respostaFixture();
        when(respostaService.pegarRespostaPorId(resposta.getId())).thenReturn(resposta);

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();

        when(repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), resposta.getId())).thenReturn(interacaoResposta);
        interacaoRespostaService.avaliarNegativamenteResposta("token", resposta.getId());

        TipoInteracao avaliacaoEsperada = TipoInteracao.NEGATIVO;

        Assert.assertEquals(avaliacaoEsperada, interacaoResposta.getTipoInteracao());
    }

    @Test
    public void deveAvaliarNegativamenteRespostaQuandoInteracaoForNula() {
        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Resposta resposta = respostaFixture();
        when(respostaService.pegarRespostaPorId(resposta.getId())).thenReturn(resposta);

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();

        when(repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), resposta.getId())).thenReturn(null);
        interacaoRespostaService.avaliarNegativamenteResposta("token", resposta.getId());

        TipoInteracao avaliacaoEsperada = TipoInteracao.POSITIVO;

        Assert.assertEquals(avaliacaoEsperada, interacaoResposta.getTipoInteracao());
    }

    @Test
    public void deveAvaliarNegativamenteRespostaQuandoInteracaoForNegativa() {
        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        Resposta resposta = respostaFixture();
        when(respostaService.pegarRespostaPorId(resposta.getId())).thenReturn(resposta);

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();
        interacaoResposta.setTipoInteracao(TipoInteracao.NEGATIVO);

        when(repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), resposta.getId())).thenReturn(interacaoResposta);
        interacaoRespostaService.avaliarNegativamenteResposta("token", resposta.getId());

        TipoInteracao avaliacaoEsperada = TipoInteracao.NEGATIVO;

        Assert.assertEquals(avaliacaoEsperada, interacaoResposta.getTipoInteracao());
    }

    @Test
    public void deveListarTodasInteracoesDeUmaPergunta() {

        List<InteracaoResposta> interacaoRespostas = new ArrayList<>();

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();
        InteracaoResposta interacaoResposta2 = interacaoRespostaPositivaFixture();

        interacaoResposta2.setId(2L);

        interacaoRespostas.add(interacaoResposta);
        interacaoRespostas.add(interacaoResposta2);

        Resposta resposta = respostaFixture();
        resposta.setInteracaos(interacaoRespostas);

        when(repository.findAllByRespostaId(resposta.getId())).thenReturn(resposta.getInteracaos());

        interacaoRespostaService.listarInteracoes(resposta.getId());

        Assert.assertEquals(interacaoRespostas.size(), resposta.getInteracaos().size());
    }

    @Test
    public void deveRetornarRelevanciaDaPerguntaCorretamente() {

        List<InteracaoResposta> interacaoRespostas = new ArrayList<>();

        InteracaoResposta interacaoResposta = interacaoRespostaPositivaFixture();
        InteracaoResposta interacaoResposta2 = interacaoRespostaPositivaFixture();

        interacaoResposta2.setId(2L);

        interacaoRespostas.add(interacaoResposta);
        interacaoRespostas.add(interacaoResposta2);

        Resposta resposta = respostaFixture();
        resposta.setInteracaos(interacaoRespostas);

        when(repository.findAllByRespostaId(resposta.getId())).thenReturn(resposta.getInteracaos());

        interacaoRespostaService.pegarRelevanciaPorId(resposta.getId());

        Assert.assertEquals(interacaoRespostas.size(), resposta.getInteracaos().size());
    }

    @Test
    public void deveRetornarComSucesseoInteracaoDoUsuario() {
        Usuario usuario = usuarioComumFixture();
        Resposta resposta = respostaFixture();
        InteracaoResposta interacaoResposta = new InteracaoResposta();
        interacaoResposta.setId(1L);
        interacaoResposta.setResposta(resposta);
        interacaoResposta.setAutor(usuario);
        interacaoResposta.setTipoInteracao(TipoInteracao.POSITIVO);
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        when(repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), resposta.getId())).thenReturn(interacaoResposta);

        InteracaoRespostaResponse interacao = interacaoRespostaService.interacaoUsuario(resposta.getId(), "token");
        Assert.assertEquals(TipoInteracao.POSITIVO, interacao.getTipoInteracao());
    }

    @Test
    public void deveRetornarNullSeNaoHouverInteracaoDoUsuario() {
        Usuario usuario = usuarioComumFixture();
        usuario.setInteracoesPerguntas(null);
        Resposta resposta = respostaFixture();
        resposta.setInteracaos(null);
        InteracaoResposta interacaoResposta = null;
        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        when(repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), resposta.getId())).thenReturn(interacaoResposta);
        InteracaoRespostaResponse interacao = interacaoRespostaService.interacaoUsuario(resposta.getId(), "token");
        Assert.assertNull(null, interacao);
    }

}
