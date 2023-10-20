package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.InteracaoResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InteracaoRespostaRepository extends JpaRepository<InteracaoResposta, Long> {


    @Query("SELECT ir from InteracaoResposta ir where ir.autor.id = :idUsuario and ir.resposta.id = :idResposta")
    InteracaoResposta pegarInteracaoPeloIdDoUsuarioEDaresposta(Long idUsuario, Long idResposta);

    @Transactional
    @Modifying
    @Query("DELETE from InteracaoResposta ir where ir.id = :id ")
    void deletarPorId(Long id);

    List<InteracaoResposta> findAllByRespostaId(Long id);
}
