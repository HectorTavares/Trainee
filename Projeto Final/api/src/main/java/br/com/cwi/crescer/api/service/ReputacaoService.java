package br.com.cwi.crescer.api.service;


import br.com.cwi.crescer.api.domain.InteracaoPergunta;
import br.com.cwi.crescer.api.domain.InteracaoResposta;
import br.com.cwi.crescer.api.domain.TipoInteracao;
import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReputacaoService {

    //criação
    private static final Double CRIAR_PERGUNTA = 4.00;
    private static final Double RECEBER_RESPOSTA = 1.00;
    private static final Double RESPONDER_PERGUNTA = 3.00;
    private static final Double RECEBER_INTERACAO_POSITIVA_PERGUNTA = 2.00;
    private static final Double RECEBER_INTERACAO_NEGATIVA_PERGUNTA = -2.00;
    private static final Double RECEBER_INTERACAO_POSITIVA_RESPOSTA = 1.00;
    private static final Double RECEBER_INTERACAO_NEGATIVA_RESPOSTA = -1.00;
    private static final Double INTERAGIR = 0.1;

    //exclusão
    private static final Double EXCLUIR_PERGUNTA = (CRIAR_PERGUNTA * -1);
    private static final Double EXCLUIR_RESPOSTA_RECEBIDA = (RECEBER_RESPOSTA * -1);
    private static final Double EXCLUIR_RESPOSTA = (RESPONDER_PERGUNTA * -1);
    private static final Double EXCLUIR_INTERACAO_POSITIVA_PERGUNTA = (RECEBER_INTERACAO_POSITIVA_PERGUNTA * -1);
    private static final Double EXCLUIR_INTERACAO_NEGATIVA_PERGUNTA = (RECEBER_INTERACAO_NEGATIVA_PERGUNTA * -1);
    private static final Double EXCLUIR_INTERACAO_POSITIVA_RESPOSTA = (RECEBER_INTERACAO_POSITIVA_RESPOSTA * -1);
    private static final Double EXCLUIR_INTERACAO_NEGATIVA_RESPOSTA = (RECEBER_INTERACAO_NEGATIVA_RESPOSTA * -1);
    private static final Double EXCLUIR_INTERACAO = (INTERAGIR * -1);


    @Autowired
    UsuarioService usuarioService;


    public void atualizarReputacao(Usuario usuario, Double quantidade) {

        usuario.setReputacao(usuario.getReputacao() + quantidade);

        usuarioService.atualizarUsuario(usuario);
    }

    public void criarPergunta(Long id) {

        Usuario usuario = usuarioService.pegarUsuarioPorId(id);

        atualizarReputacao(usuario, CRIAR_PERGUNTA);
    }

    public void excluirPergunta(Long id) {

        Usuario usuario = usuarioService.pegarUsuarioPorId(id);

        atualizarReputacao(usuario, EXCLUIR_PERGUNTA);

    }

    public void responderPergunta(Long idAutorResposta, Long idAutor) {

        Usuario autor = usuarioService.pegarUsuarioPorId(idAutor);

        Usuario autorResposta = usuarioService.pegarUsuarioPorId(idAutorResposta);

        atualizarReputacao(autor, RECEBER_RESPOSTA);

        atualizarReputacao(autorResposta, RESPONDER_PERGUNTA);

    }

    public void excluirResposta(Long idAutorResposta, Long idAutor) {

        Usuario autor = usuarioService.pegarUsuarioPorId(idAutor);

        Usuario autorResposta = usuarioService.pegarUsuarioPorId(idAutorResposta);

        atualizarReputacao(autor, EXCLUIR_RESPOSTA_RECEBIDA);

        atualizarReputacao(autorResposta, EXCLUIR_RESPOSTA);

    }

    public void interagirPergunta(InteracaoPergunta interacao) {

        Usuario usuarioAutorPergunta = usuarioService.pegarUsuarioPorId(interacao.getPergunta().getAutor().getId());

        Usuario usuarioAutorInteracao = usuarioService.pegarUsuarioPorId(interacao.getAutor().getId());


        if (interacao.getTipoInteracao() == TipoInteracao.POSITIVO) {
            atualizarReputacao(usuarioAutorPergunta, RECEBER_INTERACAO_POSITIVA_PERGUNTA);
        } else {
            atualizarReputacao(usuarioAutorPergunta, RECEBER_INTERACAO_NEGATIVA_PERGUNTA);
        }

        atualizarReputacao(usuarioAutorInteracao, INTERAGIR);
    }

    public void excluirInteracaoPergunta(InteracaoPergunta interacao) {

        Usuario usuarioAutorPergunta = usuarioService.pegarUsuarioPorId(interacao.getPergunta().getAutor().getId());

        Usuario usuarioAutorInteracao = usuarioService.pegarUsuarioPorId(interacao.getAutor().getId());


        if (interacao.getTipoInteracao() == TipoInteracao.POSITIVO) {
            atualizarReputacao(usuarioAutorPergunta, EXCLUIR_INTERACAO_POSITIVA_PERGUNTA);
        } else {
            atualizarReputacao(usuarioAutorPergunta, EXCLUIR_INTERACAO_NEGATIVA_PERGUNTA);
        }

        atualizarReputacao(usuarioAutorInteracao, EXCLUIR_INTERACAO);

    }

    public void interagirResposta(InteracaoResposta interacao) {

        Usuario usuarioAutorResposta = usuarioService.pegarUsuarioPorId(interacao.getResposta().getAutor().getId());

        Usuario usuarioAutorInteracao = usuarioService.pegarUsuarioPorId(interacao.getAutor().getId());


        if (interacao.getTipoInteracao() == TipoInteracao.POSITIVO) {
            atualizarReputacao(usuarioAutorResposta, RECEBER_INTERACAO_POSITIVA_RESPOSTA);
        } else {
            atualizarReputacao(usuarioAutorResposta, RECEBER_INTERACAO_NEGATIVA_RESPOSTA);
        }

        atualizarReputacao(usuarioAutorInteracao, INTERAGIR);
    }

    public void excluirInteracaoResposta(InteracaoResposta interacao) {

        Usuario usuarioAutorResposta = usuarioService.pegarUsuarioPorId(interacao.getResposta().getAutor().getId());

        Usuario usuarioAutorInteracao = usuarioService.pegarUsuarioPorId(interacao.getAutor().getId());


        if (interacao.getTipoInteracao() == TipoInteracao.POSITIVO) {
            atualizarReputacao(usuarioAutorResposta, EXCLUIR_INTERACAO_POSITIVA_RESPOSTA);
        } else {
            atualizarReputacao(usuarioAutorResposta, EXCLUIR_INTERACAO_NEGATIVA_RESPOSTA);
        }

        atualizarReputacao(usuarioAutorInteracao, EXCLUIR_INTERACAO);

    }
}
