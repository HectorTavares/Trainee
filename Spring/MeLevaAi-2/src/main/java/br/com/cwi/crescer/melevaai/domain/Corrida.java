package br.com.cwi.crescer.melevaai.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "corrida")
public class Corrida {

    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_passageiro")
    private Passageiro passageiro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "x_inicial")),
            @AttributeOverride(name = "y", column = @Column(name = "y_inicial"))
    })
    Coordenadas iniciais;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "x_final")),
            @AttributeOverride(name = "y", column = @Column(name = "y_final"))
    })
    Coordenadas finais;

    @Column(name="nota_motorista")
    private Integer notaMotorista;
    @Column(name="nota_passageiro")
    private Integer notaPassageiro;
    @Column(name="tempo_inicio_corrida")
    private LocalDateTime tempoInicioCorrida;


    public Corrida(final Coordenadas iniciais, final Coordenadas finais) {
        this.finais = finais;
        this.iniciais = iniciais;
    }

}