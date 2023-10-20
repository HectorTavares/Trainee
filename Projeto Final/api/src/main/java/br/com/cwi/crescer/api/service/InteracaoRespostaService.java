package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.InteracaoResposta;
import br.com.cwi.crescer.api.domain.Resposta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.InteracaoRespostaRepository;
import br.com.cwi.crescer.api.representation.interacao.resposta.InteracaoRespostaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class InteracaoRespostaService {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    RespostaService respostaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    InteracaoRespostaRepository repository;

    @Autowired
    ReputacaoService reputacaoService;

    @Autowired
    NotificacaoService notificacaoService;


    public void avaliarPositivamenteResposta(String token, Long idResposta) {


        Usuario usuarioLogado = usuarioService.pegarUsuarioLogado(token);

        Resposta resposta = respostaService.pegarRespostaPorId(idResposta);

        InteracaoResposta interacaoResposta = new InteracaoResposta();
        interacaoResposta.setTipoInteracao(TipoInteracao.POSITIVO);
        interacaoResposta.setResposta(resposta);
        interacaoResposta.setAutor(usuarioLogado);

        InteracaoResposta interacaoEncontrada = repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuarioLogado.getId(), resposta.getId());

        if (isNull(interacaoEncontrada)) {
            repository.save(interacaoResposta);
            reputacaoService.interagirResposta(interacaoResposta);
        } else if (interacaoEncontrada.getTipoInteracao() == TipoInteracao.NEGATIVO) {
            reputacaoService.excluirInteracaoResposta(interacaoEncontrada);
            interacaoEncontrada.setTipoInteracao(TipoInteracao.POSITIVO);
            reputacaoService.interagirResposta(interacaoEncontrada);
            repository.save(interacaoEncontrada);
        } else {
            reputacaoService.excluirInteracaoResposta(interacaoEncontrada);
            repository.deletarPorId(interacaoEncontrada.getId());
        }
        notificacaoService.notificarInteracaoResposta(resposta.getPergunta().getId(), resposta.getAutor().getId(), resposta.getId());
    }

    public void avaliarNegativamenteResposta(String token, Long idResposta) {

        Usuario usuarioLogado = usuarioService.pegarUsuarioLogado(token);

        Resposta resposta = respostaService.pegarRespostaPorId(idResposta);

        InteracaoResposta interacaoResposta = new InteracaoResposta();
        interacaoResposta.setTipoInteracao(TipoInteracao.NEGATIVO);
        interacaoResposta.setResposta(resposta);
        interacaoResposta.setAutor(usuarioLogado);

        InteracaoResposta interacaoEncontrada = repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuarioLogado.getId(), resposta.getId());

        if (isNull(interacaoEncontrada)) {
            repository.save(interacaoResposta);
            reputacaoService.interagirResposta(interacaoResposta);
        } else if (interacaoEncontrada.getTipoInteracao() == TipoInteracao.POSITIVO) {
            reputacaoService.excluirInteracaoResposta(interacaoEncontrada);
            interacaoEncontrada.setTipoInteracao(TipoInteracao.NEGATIVO);
            reputacaoService.interagirResposta(interacaoEncontrada);
            repository.save(interacaoEncontrada);
        } else {
            reputacaoService.excluirInteracaoResposta(interacaoEncontrada);
            repository.deletarPorId(interacaoEncontrada.getId());
        }
        notificacaoService.notificarInteracaoResposta(resposta.getPergunta().getId(), resposta.getAutor().getId(), resposta.getId());
    }


    public List<InteracaoRespostaResponse> listarInteracoes(Long idResposta) {

        List<InteracaoResposta> interacaoRespostas = repository.findAllByRespostaId(idResposta);

        return interacaoRespostas
                .stream()
                .map(interacaoResposta -> {
                    InteracaoRespostaResponse interacao = modelMapper.map(interacaoResposta, InteracaoRespostaResponse.class);
                    interacao.setEmailAutor(interacaoResposta.getAutor().getEmail());

                    return interacao;
                })
                .collect(Collectors.toList());
    }


    public Integer pegarRelevanciaPorId(Long idResposta) {

        List<InteracaoRespostaResponse> interacoes = listarInteracoes(idResposta);

        List<InteracaoRespostaResponse> interacoesPositivas = interacoes
                .stream()
                .filter(interacaoRespostaResponse -> interacaoRespostaResponse.getTipoInteracao() == TipoInteracao.POSITIVO)
                .collect(Collectors.toList());

        List<InteracaoRespostaResponse> interacoesNegativas = interacoes
                .stream()
                .filter(interacaoRespostaResponse -> interacaoRespostaResponse.getTipoInteracao() == TipoInteracao.NEGATIVO)
                .collect(Collectors.toList());

        return interacoesPositivas.size() - interacoesNegativas.size();
    }

    public InteracaoRespostaResponse interacaoUsuario(Long idResposta, String token) {

        Usuario usuario = usuarioService.pegarUsuarioLogado(token);
        InteracaoResposta interacao = repository.pegarInteracaoPeloIdDoUsuarioEDaresposta(usuario.getId(), idResposta);
        if (isNull(interacao)) {
            return null;
        }
        return modelMapper.map(interacao, InteracaoRespostaResponse.class);
    }
}
