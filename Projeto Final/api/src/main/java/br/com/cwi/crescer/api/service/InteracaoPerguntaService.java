package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.InteracaoPergunta;
import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.InteracaoPerguntaRepository;
import br.com.cwi.crescer.api.representation.interacao.pergunta.InteracaoPerguntaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class InteracaoPerguntaService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    PerguntaService perguntaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    InteracaoPerguntaRepository repository;

    @Autowired
    ReputacaoService reputacaoService;

    @Autowired
    NotificacaoService notificacaoService;


    public void avaliarPositivamentePergunta(String token, Long idPergunta) {

        Usuario usuarioLogado = usuarioService.pegarUsuarioLogado(token);

        Pergunta pergunta = perguntaService.pegarPerguntaPorId(idPergunta);

        InteracaoPergunta interacaoPergunta = new InteracaoPergunta();
        interacaoPergunta.setTipoInteracao(TipoInteracao.POSITIVO);
        interacaoPergunta.setPergunta(pergunta);
        interacaoPergunta.setAutor(usuarioLogado);

        InteracaoPergunta interacaoEncontrada = repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuarioLogado.getId(), pergunta.getId());

        if (isNull(interacaoEncontrada)) {
            repository.save(interacaoPergunta);
            reputacaoService.interagirPergunta(interacaoPergunta);
        } else if (interacaoEncontrada.getTipoInteracao() == TipoInteracao.NEGATIVO) {
            reputacaoService.excluirInteracaoPergunta(interacaoEncontrada);
            interacaoEncontrada.setTipoInteracao(TipoInteracao.POSITIVO);
            reputacaoService.interagirPergunta(interacaoEncontrada);
            repository.save(interacaoEncontrada);
        } else {
            reputacaoService.excluirInteracaoPergunta(interacaoEncontrada);
            repository.deletarPorId(interacaoEncontrada.getId());
        }

        notificacaoService.notificarInteracaoPergunta(pergunta.getId(), pergunta.getAutor().getId());
    }

    public void avaliarNegativamentePergunta(String token, Long idPergunta) {

        Usuario usuarioLogado = usuarioService.pegarUsuarioLogado(token);

        Pergunta pergunta = perguntaService.pegarPerguntaPorId(idPergunta);

        InteracaoPergunta interacaoPergunta = new InteracaoPergunta();
        interacaoPergunta.setTipoInteracao(TipoInteracao.NEGATIVO);
        interacaoPergunta.setPergunta(pergunta);
        interacaoPergunta.setAutor(usuarioLogado);

        InteracaoPergunta interacaoEncontrada = repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuarioLogado.getId(), pergunta.getId());

        if (isNull(interacaoEncontrada)) {
            repository.save(interacaoPergunta);
            reputacaoService.interagirPergunta(interacaoPergunta);
        } else if (interacaoEncontrada.getTipoInteracao() == TipoInteracao.POSITIVO) {
            reputacaoService.excluirInteracaoPergunta(interacaoEncontrada);
            interacaoEncontrada.setTipoInteracao(TipoInteracao.NEGATIVO);
            reputacaoService.interagirPergunta(interacaoEncontrada);
            repository.save(interacaoEncontrada);
        } else {
            reputacaoService.excluirInteracaoPergunta(interacaoEncontrada);
            repository.deletarPorId(interacaoEncontrada.getId());
        }
        notificacaoService.notificarInteracaoPergunta(pergunta.getId(), pergunta.getAutor().getId());
    }

    public List<InteracaoPerguntaResponse> listarInteracoes(Long idPergunta) {

        List<InteracaoPergunta> interacaoPerguntas = repository.findAllByPerguntaId(idPergunta);

        return interacaoPerguntas
                .stream()
                .map(interacaoPergunta -> {
                    InteracaoPerguntaResponse interacao = modelMapper.map(interacaoPergunta, InteracaoPerguntaResponse.class);
                    interacao.setEmailAutor(interacaoPergunta.getAutor().getEmail());

                    return interacao;
                })
                .collect(Collectors.toList());
    }

    public Integer pegarRelevanciaPorId(Long idPergunta) {

        List<InteracaoPerguntaResponse> interacoes = listarInteracoes(idPergunta);

        List<InteracaoPerguntaResponse> interacoesPositivas = interacoes
                .stream()
                .filter(interacaoPerguntaResponse -> interacaoPerguntaResponse.getTipoInteracao() == TipoInteracao.POSITIVO)
                .collect(Collectors.toList());

        List<InteracaoPerguntaResponse> interacoesNegativas = interacoes
                .stream()
                .filter(interacaoPerguntaResponse -> interacaoPerguntaResponse.getTipoInteracao() == TipoInteracao.NEGATIVO)
                .collect(Collectors.toList());

        return interacoesPositivas.size() - interacoesNegativas.size();
    }

    public InteracaoPerguntaResponse interacaoUsuario(Long idPergunta, String token) {

        Usuario usuario = usuarioService.pegarUsuarioLogado(token);
        InteracaoPergunta interacao = repository.pegarInteracaoPeloIdDoUsuarioEDapergunta(usuario.getId(), idPergunta);
        if (isNull(interacao)) {
            return null;
        }
        return modelMapper.map(interacao, InteracaoPerguntaResponse.class);
    }
}