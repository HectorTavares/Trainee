package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.TipoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    @Query("select N from Notificacao N where N.pergunta.id = ?1 and " +
            "N.destinatario.id = ?2 and N.tipoNotificao = ?3")
    Notificacao pegarNotificacaoPorPerguntaIdEDestinatarioIdETipoNotificacao(Long perguntaId, Long destinatarioId, TipoNotificacao tipoNotificacao);

    @Query("select N from Notificacao N where N.pergunta.id = ?1 and " +
            "N.destinatario.id = ?2 and N.tipoNotificao = ?3 and N.resposta.id = ?4 ")
    Notificacao pegarNotificacaoPorPerguntaIdEDestinatarioIdETipoNotificacaoERespostaId(Long perguntaId, Long destinatarioId, TipoNotificacao tipoNotificacao, Long respostaId);

    Notificacao findNotificacaoById(Long idNotificacao);

    @Transactional
    @Modifying
    void deleteByDestinatarioId(Long destinatarioId);

    @Query("select n from Notificacao n where destinatario.id = :id order by id desc")
    List<Notificacao> pegarNotificacoesUsuario(Long id);
}
