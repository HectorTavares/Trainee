package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.Resposta;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.BadRequestException;
import br.com.cwi.crescer.api.exception.NotFoundException;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.RespostaRepository;
import br.com.cwi.crescer.api.representation.resposta.RespostaRequest;
import br.com.cwi.crescer.api.representation.resposta.RespostaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespostaService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    RespostaRepository repository;

    @Autowired
    PerguntaService perguntaService;

    @Autowired
    ReputacaoService reputacaoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    NotificacaoService notificacaoService;

    @Autowired
    InteracaoRespostaService interacaoRespostaService;


    public void criarResposta(String token, RespostaRequest request) {

        Usuario usuario = usuarioService.pegarUsuarioLogado(token);

        perguntaService.verificarSePerguntaExistePorId(request.getIdPergunta());

        verificarCamposResposta(request.getFoto(), request.getDescricao());

        Pergunta pergunta = perguntaService.pegarPerguntaPorId(request.getIdPergunta());

        Resposta resposta = new Resposta();
        resposta.setDescricao(request.getDescricao());
        resposta.setFoto(request.getFoto());
        resposta.setPergunta(pergunta);
        resposta.setAutor(usuario);

        repository.save(resposta);
        reputacaoService.responderPergunta(resposta.getAutor().getId(), resposta.getPergunta().getAutor().getId());
        notificacaoService.notificarResposta(pergunta.getId(), pergunta.getAutor().getId(), usuario.getId());
    }


    public void verificarSeRespostaExistePorId(Long id) {

        boolean isRespostaExistente = repository.existsById(id);

        if (!isRespostaExistente) {
            throw new NotFoundException("Resposta não existe");
        }
    }

    public void isUsuarioAutorResposta(Long idUsuario, Long idResposta) {

        Resposta resposta = repository.findRespostaById(idResposta);

        Usuario usuario = usuarioService.pegarUsuarioPorId(idUsuario);

        boolean isNotUsuarioAutor = (!usuario.getId().equals(resposta.getAutor().getId()));
        if (isNotUsuarioAutor) {
            throw new UnauthorizedException("Não tem autorização para isso");
        }
    }


    public Resposta pegarRespostaPorId(Long idResposta) {

        return repository.getById(idResposta);
    }


    public List<RespostaResponse> listarRespostasPorId(Long idPergunta) {

        List<Resposta> respostas = repository.pegarTodasRespostasDaPerguntaPeloId(idPergunta);
        return respostas
                .stream()
                .map(resposta -> {
                    RespostaResponse respostaResponse = modelMapper.map(resposta, RespostaResponse.class);
                    respostaResponse.setRelevancia(interacaoRespostaService.pegarRelevanciaPorId(respostaResponse.getId()));
                    return respostaResponse;
                }).collect(Collectors.toList());
    }

    public Integer pegarNumeroDeRespostasPorIdPergunta(Long idPergunta) {

        return listarRespostasPorId(idPergunta).size();
    }


    public void verificarCamposResposta(String foto, String descricao) {

        if (foto.length() > 1000) {
            throw new BadRequestException("Número de caracteres inválido");
        }

        if (descricao.length() < 10 || descricao.length() > 1000) {
            throw new BadRequestException("Número de caracteres inválido para descricao");
        }

    }

    public void mudarAprovacaoDaResposta(String token, Long idResposta) {
        Usuario usuario = usuarioService.pegarUsuarioLogado(token);
        if (!usuario.getIsMonitor()) {
            throw new UnauthorizedException("usuario nao autorizado");
        }

        Resposta resposta = repository.findRespostaById(idResposta);
        resposta.setIsAprovado(!resposta.getIsAprovado());
        repository.save(resposta);
    }

}
