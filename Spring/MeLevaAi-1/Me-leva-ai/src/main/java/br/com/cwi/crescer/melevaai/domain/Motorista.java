package br.com.cwi.crescer.melevaai.domain;

import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Motorista extends Pessoa {

    private static final int IDADE_MINIMA = 18;

    @Valid
    private CarteiraNacionalHabilitacao cnh;

    @JsonIgnore
    private boolean emCorrida;


    public Motorista(String nome, @Email(message = "O e-mail deve estar em um formato válido") String email, LocalDate dataNascimento, @Valid CPF cpf, CarteiraNacionalHabilitacao cnh) {
        super(nome, email, dataNascimento, cpf);
        this.cnh = cnh;
        this.emCorrida = false;
    }

    @JsonIgnore
    public void sacar(Double quantidade){
        if(getConta()>=quantidade){
            setConta(getConta()-quantidade);
        }else{
            throw new ValidacaoNegocioException("Impossivel sacar mais do que tem em conta");
        }
    }

    @Override
    public int getIdadeMinima() {
        return IDADE_MINIMA;
    }
}
