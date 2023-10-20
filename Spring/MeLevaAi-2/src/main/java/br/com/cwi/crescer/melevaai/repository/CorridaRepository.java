package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.domain.Corrida;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CorridaRepository extends JpaRepository<Corrida, Integer> {

    Corrida findCorridaById(Integer id);

    boolean existsById(Integer id);
}
