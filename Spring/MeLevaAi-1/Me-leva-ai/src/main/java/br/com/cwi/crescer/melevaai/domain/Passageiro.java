package br.com.cwi.crescer.melevaai.domain;

import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Passageiro extends Pessoa {

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

    public void pagarCorrida(Motorista motorista, double valorCorrida) {
        this.setConta(this.getConta() - valorCorrida);
        motorista.setConta(motorista.getConta() + valorCorrida);
    }

    @Override
    public int getIdadeMinima() {
        return IDADE_MINIMA;
    }
}
