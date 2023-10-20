package br.com.cwi.crescer.melevaai.domain;

import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "passageiro")
@SequenceGenerator(name = "seq_passageiro", sequenceName = "seq_passageiro", allocationSize = 1)
public class Passageiro extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_passageiro")
    private Integer id;

    public static final int IDADE_MINIMA = 16;

    public Passageiro(String nome, String email, LocalDate dataNascimento, CPF cpf) {
        super(nome, email, dataNascimento, cpf);
    }

    @JsonIgnore
    public void depositar(Double quantidade) {
        if (quantidade > 0) {
            setConta(getConta()+quantidade);
        }else{
            throw new ValidacaoNegocioException("Valor de deposito deve ser positivo");
        }
    }


    @Override
    public int getIdadeMinima() {
        return IDADE_MINIMA;
    }
}
