package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.Tag;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.BadRequestException;
import br.com.cwi.crescer.api.exception.NotFoundException;
import br.com.cwi.crescer.api.repository.PerguntaRepository;
import br.com.cwi.crescer.api.representation.interacao.pergunta.InteracaoPerguntaResponse;
import br.com.cwi.crescer.api.representation.pergunta.PerguntaRequest;
import br.com.cwi.crescer.api.representation.pergunta.PerguntaResponse;
import br.com.cwi.crescer.api.representation.tag.TagRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static br.com.cwi.crescer.api.fixture.PerguntaFixture.perguntaFixture;
import static br.com.cwi.crescer.api.fixture.UsuarioFixture.usuarioComumFixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PerguntaServiceTest {

    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    PerguntaService perguntaService;

    @Mock
    PerguntaRepository repository;

    @Mock
    ReputacaoService reputacaoService;

    @Mock
    UsuarioService usuarioService;

    @Mock
    InteracaoPerguntaService interacaoPerguntaService;

    @Mock
    RespostaService respostaService;

    @Test
    public void deveCriarPerguntaCorretamente() {
        List<TagRequest> tags = new ArrayList<>();
        TagRequest tag = new TagRequest();
        tag.setId(1L);
        tag.setNome("Discord");
        tags.add(tag);

        Usuario usuario = usuarioComumFixture();

        when(usuarioService.pegarUsuarioLogado("token")).thenReturn(usuario);
        PerguntaRequest request = new PerguntaRequest();
        request.setTitulo("teste titulo");
        request.setDescricao("teste descrição");
        request.setFoto("foto.png");
        request.setTags(tags);
        perguntaService.criarPergunta(request, "token");
        Pergunta pergunta = modelMapper.map(request, Pergunta.class);
        verify(repository, times(1)).save(any());

    }

    @Test(expected = NotFoundException.class)
    public void deveVerificarSePerguntaNaoExiste() {
        Pergunta pergunta = perguntaFixture();
        when(repository.existsById(pergunta.getId())).thenReturn(false);
        perguntaService.verificarSePerguntaExistePorId(pergunta.getId());
    }

    @Test
    public void deveVerificarSePerguntaExiste() {
        Pergunta pergunta = perguntaFixture();
        when(repository.existsById(pergunta.getId())).thenReturn(true);
        perguntaService.verificarSePerguntaExistePorId(pergunta.getId());
    }

    @Test
    public void deveRetornarPerguntaSeElaExistir() {
        Pergunta pergunta = perguntaFixture();
        when(repository.existsById(pergunta.getId())).thenReturn(true);
        when(repository.findById(pergunta.getId())).thenReturn(java.util.Optional.of(pergunta));
        perguntaService.pegarPerguntaPorId(pergunta.getId());
    }

    @Test(expected = NotFoundException.class)
    public void deveRetornarErroSeAPerguntaNaoExistir() {
        Pergunta pergunta = perguntaFixture();
        when(repository.existsById(pergunta.getId())).thenReturn(false);
        perguntaService.pegarPerguntaPorId(pergunta.getId());
    }

    @Test(expected = BadRequestException.class)
    public void deveRetornarErroQuandoTamanhoDaDescricaoNaoObedecerOsLimites() {
        List<TagRequest> tags = new ArrayList<>();
        TagRequest tag = new TagRequest();
        tag.setId(1L);
        tag.setNome("Discord");
        tags.add(tag);

        PerguntaRequest request = new PerguntaRequest();
        request.setTitulo("teste titulo");
        request.setDescricao("teste");
        request.setFoto("foto.png");
        request.setTags(tags);
        perguntaService.verificarCamposPergunta(request);
    }

    @Test(expected = BadRequestException.class)
    public void deveRetornarErroQuandoQuantidadeDeTagsNaoObedecerOsLimites() {
        PerguntaRequest request = new PerguntaRequest();
        request.setTitulo("teste titulo");
        request.setDescricao("teste da descrição");
        request.setFoto("foto.png");
        request.setTags(new ArrayList<>());
        perguntaService.verificarCamposPergunta(request);
    }

    @Test(expected = BadRequestException.class)
    public void deveRetornarErroQuandoTamanhoDoTituloNaoObedecerOsLimites() {
        List<TagRequest> tags = new ArrayList<>();
        TagRequest tag = new TagRequest();
        tag.setId(1L);
        tag.setNome("Discord");
        tags.add(tag);

        PerguntaRequest request = new PerguntaRequest();
        request.setTitulo("teste");
        request.setDescricao("teste da descrição");
        request.setFoto("foto.png");
        request.setTags(tags);
        perguntaService.verificarCamposPergunta(request);
    }

    @Test(expected = BadRequestException.class)
    public void deveRetornarErroQuandoTamanhoDaFotoNaoObedecerOsLimites() {
        List<TagRequest> tags = new ArrayList<>();
        TagRequest tag = new TagRequest();
        tag.setId(1L);
        tag.setNome("Discord");
        tags.add(tag);

        PerguntaRequest request = new PerguntaRequest();
        request.setTitulo("teste do titulo");
        request.setDescricao("teste da descrição");
        request.setFoto("Aenean placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti. Morbi mattis felis at nunc. Duis viverra diam non justo. In nisl. Nullam sit amet magna in magna gravida vehicula. Mauris tincidunt sem sed arcu. Nunc posuere. Nullam lectus justo, vulputate eget, mollis sed, tempor sed, magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam neque. Curabitur ligula sapien, pulvinar a, vestibulum quis, facilisis vel, sapien. Nullam eget nisl. Donec vitae arcu. Aenean placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti. Morbi mattis felis at nunc. Duis viverra diam non justo. In nisl. Nullam sit amet magna in magna gravida vehicula. Mauris tincidunt sem sed arcu. Nunc posuere. Nullam lectus justo, vulputate eget, mollis sed, tempor sed, magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam neque. Curabitur ligula sapien, pulvinar a, vestibulum quis, facilisis");
        request.setTags(tags);
        perguntaService.verificarCamposPergunta(request);
    }

    @Test
    public void deveEncontrarPerguntaResponsePorId() {
        Pergunta pergunta = perguntaFixture();
        when(repository.existsById(pergunta.getId())).thenReturn(true);
        when(repository.findById(pergunta.getId())).thenReturn(java.util.Optional.of(pergunta));
        PerguntaResponse perguntaResponse = new PerguntaResponse();
        perguntaResponse.setId(1L);

        InteracaoPerguntaResponse interacaoPerguntaResponse = new InteracaoPerguntaResponse();
        interacaoPerguntaResponse.setId(1L);
        interacaoPerguntaResponse.setTipoInteracao(TipoInteracao.POSITIVO);
        interacaoPerguntaResponse.setEmailAutor("emailDeTeste@gmail.com");
        List<InteracaoPerguntaResponse> interacaoPerguntaResponseList = new ArrayList<>();
        interacaoPerguntaResponseList.add(interacaoPerguntaResponse);

        when(interacaoPerguntaService.pegarRelevanciaPorId(pergunta.getId())).thenReturn(1);


        perguntaService.buscarPerguntaResponsePorId(pergunta.getId());
    }

    @Test
    public void deveBuscarPorTituloETagsQuandoHouverTags(){

        List<Long> tagList = new ArrayList<>();
        tagList.add(123L);

        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };

        List<Pergunta> perguntas = new ArrayList<>();
        Pergunta pergunta = perguntaFixture();
        perguntas.add(pergunta);

        Page<Pergunta> page = new PageImpl<>(perguntas);

        when(respostaService.pegarNumeroDeRespostasPorIdPergunta(any()))
                .thenReturn(1);

        when(interacaoPerguntaService.pegarRelevanciaPorId(any()))
                .thenReturn(1);

        when(repository.pesquisarPerguntasPorTituloOuDescricaoEtags(any(), any(), any()))
                .thenReturn(page);

        Page<PerguntaResponse> response = perguntaService.pesquisarPerguntas("teste", tagList, pageable);


        verify(repository, times(1)).pesquisarPerguntasPorTituloOuDescricaoEtags(any(), any(), any());

        assertTrue(response instanceof Page);
    }

    @Test
    public void deveBuscarPortTituloQuandoNaoHouverTags(){

        List<Long> tagList = new ArrayList<>();

        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        List<Pergunta> perguntas = new ArrayList<>();
        Pergunta pergunta = perguntaFixture();
        perguntas.add(pergunta);
        Page<Pergunta> page = new PageImpl<>(perguntas);

        when(respostaService.pegarNumeroDeRespostasPorIdPergunta(pergunta.getId()))
                .thenReturn(1);

        when(interacaoPerguntaService.pegarRelevanciaPorId(pergunta.getId()))
                .thenReturn(1);

        when(repository.pesquisarPerguntasPorTituloOuDescricao(any(), any()))
                .thenReturn(page);

        List<PerguntaResponse> response = perguntaService.pesquisarPerguntas("teste", tagList, pageable).getContent();


        verify(repository, times(1)).pesquisarPerguntasPorTituloOuDescricao(any(), any());

        assertEquals(response.get(0).getId(),  pergunta.getId());
    }

    @Test
    public void deveChamarListaDeTodasAsPerguntasCorretamente(){

        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        List<Pergunta> perguntas = new ArrayList<>();
        Pergunta pergunta = perguntaFixture();
        perguntas.add(pergunta);
        Page<Pergunta> page = new PageImpl<>(perguntas);

        when(repository.findAllByOrderByIdDesc(pageable))
                .thenReturn(page);

        when(interacaoPerguntaService.pegarRelevanciaPorId(pergunta.getId()))
                .thenReturn(1);

        List<PerguntaResponse> response = perguntaService.listarTodasPerguntas(pageable).getContent();

        verify(repository, times(1)).findAllByOrderByIdDesc(pageable);

        assertEquals(response.get(0).getId(), pergunta.getId());

    }


}