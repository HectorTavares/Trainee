package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.Pergunta;
import br.com.cwi.crescer.api.domain.TipoNotificacao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.UnauthorizedException;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import br.com.cwi.crescer.api.representation.notificacao.NotificacaoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class NotificacaoService {


    public static final String MENSAGEM_RESPOSTA = "Resposta(s) novas em:  ";

    public static final String MENSAGEM_INTERACAO_PERGUNTA = "Interações novas em: ";

    public static final String MENSAGEM_INTERACAO_RESPOSTA = "Novas interações na sua resposta em:  ";


    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    NotificacaoRepository repository;


    @Autowired
    PerguntaService perguntaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RespostaService respostaService;


    public Notificacao criarNotificacao(Long idPergunta, Long idUsuarioDestinatario) {
        Notificacao notificacao = new Notificacao();
        Usuario destinatario = usuarioService.pegarUsuarioPorId(idUsuarioDestinatario);
        Pergunta pergunta = perguntaService.pegarPerguntaPorId(idPergunta);

        notificacao.setPergunta(pergunta);
        notificacao.setDestinatario(destinatario);

        return notificacao;
    }


    public void notificarResposta(Long idPergunta, Long idUsuarioDestinatario, Long idAutorAcao) {

        if (!idAutorAcao.equals(idUsuarioDestinatario)) {
            Notificacao notificacaoExistente = repository
                    .pegarNotificacaoPorPerguntaIdEDestinatarioIdETipoNotificacao(
                            idPergunta,
                            idUsuarioDestinatario,
                            TipoNotificacao.RESPOSTA);

            if (isNull(notificacaoExistente)) {
                Notificacao notificacao = criarNotificacao(idPergunta, idUsuarioDestinatario);
                notificacao.setMensagem(MENSAGEM_RESPOSTA + notificacao.getPergunta().getTitulo());
                notificacao.setTipoNotificao(TipoNotificacao.RESPOSTA);
                repository.save(notificacao);
            }
        }

    }


    public void notificarInteracaoPergunta(Long idPergunta, Long idUsuarioDestinatario) {

        Notificacao notificacaoExistente = repository
                .pegarNotificacaoPorPerguntaIdEDestinatarioIdETipoNotificacao(
                        idPergunta,
                        idUsuarioDestinatario,
                        TipoNotificacao.INTERACAO_PERGUNTA);

        if (isNull(notificacaoExistente)) {
            Notificacao notificacao = criarNotificacao(idPergunta, idUsuarioDestinatario);
            notificacao.setMensagem(MENSAGEM_INTERACAO_PERGUNTA + notificacao.getPergunta().getTitulo());
            notificacao.setTipoNotificao(TipoNotificacao.INTERACAO_PERGUNTA);
            repository.save(notificacao);
        }
    }

    public void notificarInteracaoResposta(Long idPergunta, Long idUsuarioDestinatario, Long idResposta) {

        Notificacao notificacaoExistente = repository
                .pegarNotificacaoPorPerguntaIdEDestinatarioIdETipoNotificacaoERespostaId(
                        idPergunta,
                        idUsuarioDestinatario,
                        TipoNotificacao.INTERACAO_PERGUNTA,
                        idResposta);

        if (isNull(notificacaoExistente)) {
            Notificacao notificacao = criarNotificacao(idPergunta, idUsuarioDestinatario);
            notificacao.setResposta(respostaService.pegarRespostaPorId(idResposta));
            notificacao.setMensagem(MENSAGEM_INTERACAO_RESPOSTA + notificacao.getPergunta().getTitulo());
            notificacao.setTipoNotificao(TipoNotificacao.INTERACAO_RESPOSTA);
            repository.save(notificacao);
        }
    }


    public void visualizarNotificacaoPorId(Long idNotificacao, String token) {

        isUsuarioDestinatarioNotificacao(idNotificacao, token);
        repository.deleteById(idNotificacao);
    }

    public void visualizarTodasNotificacoesPorIdDestinatario(String token) {

        Usuario destinatario = usuarioService.pegarUsuarioLogado(token);

        repository.deleteByDestinatarioId(destinatario.getId());
    }


    public void isUsuarioDestinatarioNotificacao(Long idNotificacao, String token) {

        Usuario destinatario = usuarioService.pegarUsuarioLogado(token);

        Notificacao notificacao = repository.findNotificacaoById(idNotificacao);


        boolean isNotUsuarioDestinatario = (!destinatario.getId().equals(notificacao.getDestinatario().getId()));
        if (isNotUsuarioDestinatario) {
            throw new UnauthorizedException("Não tem autorização para isso");
        }
    }

    public List<NotificacaoResponse> pegarTodasNotificacoes(String token) {

        Usuario usuario = usuarioService.pegarUsuarioLogado(token);

        return repository.
                pegarNotificacoesUsuario(usuario.getId())
                .stream()
                .map(notificacao -> modelMapper.map(notificacao, NotificacaoResponse.class))
                .collect(Collectors.toList());
    }
}
