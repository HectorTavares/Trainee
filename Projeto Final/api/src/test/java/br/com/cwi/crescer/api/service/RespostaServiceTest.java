package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.exception.BadRequestException;
import br.com.cwi.crescer.api.exception.NotFoundException;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import br.com.cwi.crescer.api.repository.RespostaRepository;
import br.com.cwi.crescer.api.representation.interacao.resposta.InteracaoRespostaResponse;
import br.com.cwi.crescer.api.representation.resposta.RespostaRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.RespostaFixture.respostaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioMonitorFixture;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RespostaServiceTest {

    @InjectMocks
    private RespostaService respostaService;

    @Mock
    private RespostaRepository repository;

    @Mock
    private PerguntaService perguntaService;

    @Mock
    ReputacaoService reputacaoService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    InteracaoRespostaService interacaoRespostaService;

    @Mock
    NotificacaoService notificacaoService;


    @Test
    public void deveCriarRespostaCorretamente() {
        Usuario usuario = usuarioComumFixture();
        RespostaRequest request = new RespostaRequest();
        request.setIdPergunta(1L);
        request.setDescricao("Descricao do teste de service da resposta");
        request.setFoto("Foto do teste de service da resposta");

        Pergunta pergunta = perguntaFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        respostaService.verificarCamposResposta(request.getFoto(), request.getDescricao());
        when(perguntaService.pegarPerguntaPorId(request.getIdPergunta())).thenReturn(pergunta);

        Resposta resposta = new Resposta();
        resposta.setDescricao(request.getDescricao());
        resposta.setFoto(request.getFoto());
        resposta.setPergunta(pergunta);
        resposta.setAutor(usuario);
        resposta.setId(42L);

        doNothing().when(reputacaoService).responderPergunta(any(), any());
        doNothing().when(notificacaoService).notificarResposta(any(), any(), any());
        respostaService.criarResposta("token", request);
        verify(repository, times(1)).save(any());
    }

    @Test(expected = NotFoundException.class)
    public void deveVerificarSeRespostaNaoExiste() {
        Resposta resposta = respostaFixture();
        when(repository.existsById(any())).thenReturn(false);
        respostaService.verificarSeRespostaExistePorId(resposta.getId());
    }

    @Test
    public void deveVerificarSeRespostaExiste() {
        Resposta resposta = respostaFixture();
        when(repository.existsById(resposta.getId())).thenReturn(true);
        respostaService.verificarSeRespostaExistePorId(resposta.getId());
    }

    @Test
    public void deveAprovarSeUsuarioForAutorDaResposta() {
        Usuario usuario = usuarioComumFixture();
        usuario.setId(134243423423423342L);
        Resposta resposta = respostaFixture();
        when(repository.findRespostaById(resposta.getId())).thenReturn(resposta);
        when(usuarioService.pegarUsuarioPorId(usuario.getId())).thenReturn(usuario);
        respostaService.isUsuarioAutorResposta(usuario.getId(), resposta.getId());
    }

    @Test(expected = UnauthorizedException.class)
    public void deveRetornarErroSeUsuarioNaoForAutorDaResposta() {
        Usuario usuario = usuarioComumFixture();
        usuario.setId(123L);
        Resposta resposta = respostaFixture();
        when(repository.findRespostaById(resposta.getId())).thenReturn(resposta);
        when(usuarioService.pegarUsuarioPorId(usuario.getId())).thenReturn(usuario);
        respostaService.isUsuarioAutorResposta(usuario.getId(), resposta.getId());
    }

    @Test
    public void pegarRespostaPorId() {
        Resposta resposta = respostaFixture();
        when(repository.getById(resposta.getId())).thenReturn(resposta);
        Resposta respostaEsperada = respostaService.pegarRespostaPorId(resposta.getId());
        Assert.assertEquals(respostaEsperada, resposta);
    }

    @Test
    public void listarRespostasPorId() {
        List<Resposta> respostas = new ArrayList<>();

        Pergunta pergunta = perguntaFixture();

        Resposta resposta = respostaFixture();
        Resposta resposta1 = respostaFixture();

        respostas.add(resposta);
        respostas.add(resposta1);

        pergunta.setRespostas(respostas);
        when(repository.pegarTodasRespostasDaPerguntaPeloId(pergunta.getId())).thenReturn(respostas);

        InteracaoRespostaResponse interacaoRespostaResponse = new InteracaoRespostaResponse();
        interacaoRespostaResponse.setId(1L);
        interacaoRespostaResponse.setTipoInteracao(TipoInteracao.POSITIVO);
        interacaoRespostaResponse.setEmailAutor("emailDeTeste@gmail.com");
        List<InteracaoRespostaResponse> interacaoRespostaResponseList = new ArrayList<>();
        interacaoRespostaResponseList.add(interacaoRespostaResponse);

        when(interacaoRespostaService.pegarRelevanciaPorId(resposta.getId())).thenReturn(1);

        respostaService.listarRespostasPorId(resposta.getId());
    }

    @Test
    public void pegarNumeroDeRespostasPorIdPergunta() {

        List<Resposta> respostas = new ArrayList<>();

        Pergunta pergunta = perguntaFixture();

        Resposta resposta = respostaFixture();
        Resposta resposta1 = respostaFixture();

        respostas.add(resposta);
        respostas.add(resposta1);

        pergunta.setRespostas(respostas);
        when(repository.pegarTodasRespostasDaPerguntaPeloId(pergunta.getId())).thenReturn(respostas);

        InteracaoRespostaResponse interacaoRespostaResponse = new InteracaoRespostaResponse();
        interacaoRespostaResponse.setId(1L);
        interacaoRespostaResponse.setTipoInteracao(TipoInteracao.POSITIVO);
        interacaoRespostaResponse.setEmailAutor("emailDeTeste@gmail.com");
        List<InteracaoRespostaResponse> interacaoRespostaResponseList = new ArrayList<>();
        interacaoRespostaResponseList.add(interacaoRespostaResponse);

        when(interacaoRespostaService.pegarRelevanciaPorId(resposta.getId())).thenReturn(1);

        Integer numeroDeRespostasRetornado = respostaService.pegarNumeroDeRespostasPorIdPergunta(resposta.getId());

        Assert.assertEquals(java.util.Optional.of(respostas.size()), java.util.Optional.of(numeroDeRespostasRetornado));

    }

    @Test
    public void verificarSeTodosCamposDaRespostaSaoValidos() {
        Resposta resposta = respostaFixture();
        respostaService.verificarCamposResposta(resposta.getFoto(), resposta.getDescricao());
    }

    @Test(expected = BadRequestException.class)
    public void verificarSeCampoDaFotoTemTamanhoAdequado() {
        Resposta resposta = respostaFixture();
        resposta.setFoto("Aenean placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti. Morbi mattis felis at nunc. Duis viverra diam non justo. In nisl. Nullam sit amet magna in magna gravida vehicula. Mauris tincidunt sem sed arcu. Nunc posuere. Nullam lectus justo, vulputate eget, mollis sed, tempor sed, magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam neque. Curabitur ligula sapien, pulvinar a, vestibulum quis, facilisis vel, sapien. Nullam eget nisl. Donec vitae arcu. Aenean placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti. Morbi mattis felis at nunc. Duis viverra diam non justo. In nisl. Nullam sit amet magna in magna gravida vehicula. Mauris tincidunt sem sed arcu. Nunc posuere. Nullam lectus justo, vulputate eget, mollis sed, tempor sed, magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam neque. Curabitur ligula sapien, pulvinar a, vestibulum quis, facilisis");
        respostaService.verificarCamposResposta(resposta.getFoto(), resposta.getDescricao());
    }

    @Test(expected = BadRequestException.class)
    public void verificarSeCampoDaDescricaoTemTamanhoAdequado() {
        Resposta resposta = respostaFixture();
        resposta.setDescricao("teste");
        respostaService.verificarCamposResposta(resposta.getFoto(), resposta.getDescricao());
    }

    @Test
    public void deveMudarAprovacaoDaRespostaCorretamente() {
        Usuario usuario = usuarioMonitorFixture();
        Resposta resposta = respostaFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        when(repository.findRespostaById(resposta.getId())).thenReturn(resposta);

        respostaService.mudarAprovacaoDaResposta("token", resposta.getId());
        verify(repository, times(1)).save(resposta);

    }

    @Test(expected = UnauthorizedException.class)
    public void deveRetornarErroAoMudarAprovacaoDaRespostaSeNaoForUsuarioAutorizado() {
        Usuario usuario = usuarioComumFixture();
        Resposta resposta = respostaFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);

        respostaService.mudarAprovacaoDaResposta("token", resposta.getId());
        verify(repository, times(0)).save(resposta);
    }



}
