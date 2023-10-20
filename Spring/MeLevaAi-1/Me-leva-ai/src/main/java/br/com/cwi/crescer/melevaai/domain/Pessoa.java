package br.com.cwi.crescer.melevaai.domain;

import java.time.LocalDate;
import java.time.Period;

import br.com.cwi.crescer.melevaai.exception.IdadeMinimaException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Pessoa {

    private String nome;
    private String email;
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @Valid
    @EqualsAndHashCode.Include
    private CPF cpf;

    private Double conta;

    public Pessoa(String nome, String email, LocalDate dataNascimento, CPF cpf) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.conta = 0.00;
    }

    @JsonIgnore
    public int getIdade() { return Period.between(this.dataNascimento, LocalDate.now()).getYears(); }

    @JsonIgnore
    public abstract int getIdadeMinima();

    public void validaIdadeMinima() throws IdadeMinimaException {
        int idadeMinima = this.getIdadeMinima();

        if(this.getIdade() < idadeMinima) {
            throw new IdadeMinimaException("Idade minima inválida");
        }
    }
}
