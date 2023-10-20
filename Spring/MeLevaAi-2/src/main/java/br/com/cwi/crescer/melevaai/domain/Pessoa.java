package br.com.cwi.crescer.melevaai.domain;

import java.time.LocalDate;
import java.time.Period;

import br.com.cwi.crescer.melevaai.exception.IdadeMinimaException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import lombok.*;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "nome")
    private String nome;

    @Column(name="data_nascimento")
    private LocalDate dataNascimento;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "numero", column = @Column(name = "cpf"))
    })
    private CPF cpf;

    @Column(name="conta")
    private Double conta;

    public Pessoa(String nome, String email, LocalDate dataNascimento, CPF cpf) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.conta = 0.00;
    }

    public Pessoa(){
        this.conta=0.00;
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
