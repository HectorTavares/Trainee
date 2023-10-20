package br.com.cwi.crescer.melevaai.domain;

import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "motorista")
public class Motorista extends Pessoa {



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cnh")
    private CarteiraNacionalHabilitacao cnh;

    @Column(name = "em_corrida")
    private boolean emCorrida;

    @OneToMany(mappedBy = "proprietario")
    private List<Veiculo> veiculos;


    private static final int IDADE_MINIMA = 18;

    public Motorista(String nome, @Email(message = "O e-mail deve estar em um formato válido") String email, LocalDate dataNascimento, @Valid CPF cpf, CarteiraNacionalHabilitacao cnh) {
        super(nome, email, dataNascimento, cpf);
        this.cnh = cnh;
        this.emCorrida = false;
    }

    public Motorista(){
        super();
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
