package br.com.cwi.crescer.melevaai.repository;

import java.util.List;

import br.com.cwi.crescer.melevaai.domain.CPF;
import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.domain.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageiroRepository extends JpaRepository<Passageiro, Integer> {

    Passageiro findByCpf(CPF cpf);

}
