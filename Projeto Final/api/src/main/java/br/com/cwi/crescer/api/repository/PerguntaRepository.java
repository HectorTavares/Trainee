package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Pergunta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {


    Page<Pergunta> findAllByOrderByIdDesc(Pageable pageable);

    void delete(Pergunta pergunta);


    @Query(value = "select p.*, " +
            "((select count(*) from interacao_pergunta ip where ip.pergunta_id  = p.id and tipo_interacao = 0) " +
            "- (select count(*) from interacao_pergunta ip where ip.pergunta_id  = p.id and tipo_interacao = 1)) as relevancia " +
            "from pergunta p  " +
            "inner join pergunta_tags pt " +
            "on p.id = pt.pergunta_id  " +
            "where  " +
            "(lower(p.descricao) like lower(concat('%',:busca,'%')) or lower(p.titulo) like lower(concat('%',:busca,'%'))) " +
            "and " +
            "pt.tags_id in(:tags) " +
            "order by relevancia desc ", countQuery = "SELECT count(*) FROM pergunta ", nativeQuery = true)
    Page<Pergunta> pesquisarPerguntasPorTituloOuDescricaoEtags(String busca, List<Long> tags, Pageable pageable);

    @Query(value = "select distinct p.*,((select count(*) from interacao_pergunta ip where ip.pergunta_id  = p.id and tipo_interacao = 0) " +
            " - (select count(*) from interacao_pergunta ip where ip.pergunta_id  = p.id  and tipo_interacao = 1)) as relevancia from" +
            " pergunta p inner join pergunta_tags pt on p.id = pt.pergunta_id where (lower(p.descricao) like lower(concat('%',:busca,'%'))" +
            " or lower(p.titulo) like lower(concat('%',:busca,'%'))) " +
            "order by relevancia desc ", countQuery = "SELECT count(*) FROM pergunta ", nativeQuery = true)
    Page<Pergunta> pesquisarPerguntasPorTituloOuDescricao(String busca, Pageable pageable);


}
