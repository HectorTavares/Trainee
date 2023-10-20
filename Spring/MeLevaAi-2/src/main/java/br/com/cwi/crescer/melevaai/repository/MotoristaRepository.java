package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {

    Motorista findByCpf(CPF cpf);

    Boolean existsByCpf(CPF cpf);

//    Motorista save(Motorista motorista);
//
//    List<Motorista> findAll();
//
//    void delete(Motorista motorista);

}
