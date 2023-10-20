package br.com.cwi.crescer.melevaai.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Corrida {
    private int idCorrida;
    private String cpfPassageiro;
    private double iniciais [] = new double [2];
    private double finais [] = new double [2];
    private Integer notaMotorista;
    private Integer notaPassageiro;
    private LocalDateTime tempoInicioCorrida;
    private Veiculo veiculo;

    public Corrida(final double[] iniciais, final double[] finais) {
        this.iniciais = iniciais;
        this.finais = finais;
    }

}