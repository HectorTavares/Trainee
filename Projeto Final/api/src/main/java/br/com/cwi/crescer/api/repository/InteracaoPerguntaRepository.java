package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.InteracaoPergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface InteracaoPerguntaRepository extends JpaRepository<InteracaoPergunta, Long> {

    @Query("SELECT ip from InteracaoPergunta ip where ip.autor.id = :idUsuario and ip.pergunta.id = :idPergunta")
    InteracaoPergunta pegarInteracaoPeloIdDoUsuarioEDapergunta(Long idUsuario, Long idPergunta);

    @Transactional
    @Modifying
    @Query("DELETE from InteracaoPergunta ip where ip.id = :id ")
    void deletarPorId(Long id);

    List<InteracaoPergunta> findAllByPerguntaId(Long id);
}