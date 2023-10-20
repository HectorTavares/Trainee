package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Resposta findRespostaById(Long id);

    List<Resposta> findAll();

    void delete(Resposta resposta);

    @Query(value = "select * from resposta r where r.pergunta_id = :idPergunta", nativeQuery = true)
    List<Resposta> pegarTodasRespostasDaPerguntaPeloId(Long idPergunta);

    void deleteById(Long id);

}
