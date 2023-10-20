package br.com.cwi.crescer.api.service;


import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.Tag;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.BadRequestException;
import br.com.cwi.crescer.api.exception.NotFoundException;
import br.com.cwi.crescer.api.repository.PerguntaRepository;
import br.com.cwi.crescer.api.representation.pergunta.PerguntaRequest;
import br.com.cwi.crescer.api.representation.pergunta.PerguntaResponse;
import br.com.cwi.crescer.api.representation.tag.TagResponse;
import br.com.cwi.crescer.api.representation.usuario.UsuarioResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerguntaService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    PerguntaRepository repository;

    @Autowired
    ReputacaoService reputacaoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    InteracaoPerguntaService interacaoPerguntaService;

    @Autowired
    RespostaService respostaService;

    public static final String PERGUNTA_NAO_EXISTE = "Pergunta não existe";

    public void criarPergunta(PerguntaRequest request, String token) {

        Usuario usuario = usuarioService.pegarUsuarioLogado(token);

        usuarioService.verificarSeUsuarioEstaCadastrado(usuario.getId());

        verificarCamposPergunta(request);

        List<Tag> tags = request.getTags()
                .stream()
                .map(tag -> modelMapper.map(tag, Tag.class))
                .collect(Collectors.toList());

        Pergunta pergunta = modelMapper.map(request, Pergunta.class);
        pergunta.setAutor(usuario);

        pergunta.setTags(tags);

        repository.save(pergunta);
        reputacaoService.criarPergunta(usuario.getId());

    }

    public Page<PerguntaResponse> pesquisarPerguntas(String busca, List<Long> tags, Pageable pageable) {

        Page<PerguntaResponse> perguntas;

        if (tags.isEmpty()) {
            Page<Pergunta> pagina = repository.pesquisarPerguntasPorTituloOuDescricao(busca, pageable);
            perguntas = pagina
                    .map(pergunta -> {
                        PerguntaResponse perguntaResponse = modelMapper.map(pergunta, PerguntaResponse.class);
                        perguntaResponse.setContadorRelevancia(interacaoPerguntaService.pegarRelevanciaPorId(pergunta.getId()));
                        perguntaResponse.setContadorRespostas(respostaService.pegarNumeroDeRespostasPorIdPergunta(pergunta.getId()));

                        return perguntaResponse;
                    });
        } else {
            Page<Pergunta> pagina = repository.pesquisarPerguntasPorTituloOuDescricaoEtags(busca, tags, pageable);
            perguntas = pagina
                    .map(pergunta -> {
                        PerguntaResponse perguntaResponse = modelMapper.map(pergunta, PerguntaResponse.class);
                        perguntaResponse.setContadorRespostas(respostaService.pegarNumeroDeRespostasPorIdPergunta(pergunta.getId()));
                        perguntaResponse.setContadorRelevancia(interacaoPerguntaService.pegarRelevanciaPorId(pergunta.getId()));

                        return perguntaResponse;
                    });
        }

        return perguntas;

    }

    public void verificarSePerguntaExistePorId(Long id) {

        boolean isNotPerguntaExistente = !repository.existsById(id);

        if (isNotPerguntaExistente) {
            throw new NotFoundException(PERGUNTA_NAO_EXISTE);
        }
    }

    public Pergunta pegarPerguntaPorId(Long idPergunta) {

        verificarSePerguntaExistePorId(idPergunta);
        return repository.findById(idPergunta)
                .orElseThrow(() -> new NotFoundException(PERGUNTA_NAO_EXISTE));
    }

    public PerguntaResponse buscarPerguntaResponsePorId(Long idPergunta) {

        verificarSePerguntaExistePorId(idPergunta);
        Pergunta pergunta = repository.findById(idPergunta)
                .orElseThrow(() -> new NotFoundException(PERGUNTA_NAO_EXISTE));
        PerguntaResponse perguntaResponse = modelMapper.map(pergunta, PerguntaResponse.class);
        perguntaResponse.setContadorRespostas(pergunta.getRespostas().size());
        perguntaResponse.setContadorRelevancia(interacaoPerguntaService.pegarRelevanciaPorId(perguntaResponse.getId()));
        return perguntaResponse;
    }

    public Page<PerguntaResponse> listarTodasPerguntas(Pageable page) {

        Page<Pergunta> perguntas = repository.findAllByOrderByIdDesc(page);
        return perguntas
                .map(pergunta -> {
                    UsuarioResponse usuario = modelMapper.map(pergunta.getAutor(), UsuarioResponse.class);
                    PerguntaResponse perguntaResponse = modelMapper.map(pergunta, PerguntaResponse.class);
                    List<TagResponse> tags = pergunta
                            .getTags()
                            .stream()
                            .map(tag -> modelMapper.map(tag, TagResponse.class))
                            .collect(Collectors.toList());
                    perguntaResponse.setTags(tags);
                    perguntaResponse.setAutor(usuario);
                    perguntaResponse.setContadorRespostas(pergunta.getRespostas().size());
                    perguntaResponse.setContadorRelevancia(interacaoPerguntaService.pegarRelevanciaPorId(perguntaResponse.getId()));
                    return perguntaResponse;
                });
    }

    public void verificarCamposPergunta(PerguntaRequest request) {

        if (request.getDescricao().length() < 10 || request.getDescricao().length() > 1000) {
            throw new BadRequestException("Número de caracteres inválido para descricao");
        }

        if (request.getTags().isEmpty() || request.getTags().size() > 5) {
            throw new BadRequestException("Numero de tags inválido");
        }

        if (request.getTitulo().length() < 10 || request.getTitulo().length() > 100) {
            throw new BadRequestException("Número de caracteres inválido para titulo");
        }

        if (request.getFoto().length() > 1000) {
            throw new BadRequestException("Número de caracteres inválido");
        }
    }


}