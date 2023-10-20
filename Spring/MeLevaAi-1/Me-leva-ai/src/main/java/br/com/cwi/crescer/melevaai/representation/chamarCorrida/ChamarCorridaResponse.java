package br.com.cwi.crescer.melevaai.representation.chamarCorrida;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChamarCorridaResponse {
    public ChamarCorridaResponse(int idCorrida, VeiculoChamarCorridaResponse veiculo, double tempoCorrida) {
        this.idCorrida = idCorrida;
        this.veiculo = veiculo;
        this.tempoCorrida = tempoCorrida;
    }

    @NotNull
    private int idCorrida;
    @NotNull
    private VeiculoChamarCorridaResponse veiculo;
    @NotNull
    private double tempoCorrida;
}
